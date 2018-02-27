package com.iut.ecommerce.ecommerce.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import com.github.lzyzsd.circleprogress.DonutProgress;
import com.iut.ecommerce.ecommerce.R;
import com.iut.ecommerce.ecommerce.dao.ArticleDao;
import com.iut.ecommerce.ecommerce.fragment.ArticleView;
import com.iut.ecommerce.ecommerce.fragment.CategorieView;
import com.iut.ecommerce.ecommerce.modele.Article;
import com.iut.ecommerce.ecommerce.modele.Categorie;
import com.iut.ecommerce.ecommerce.utils.ActiviteEnAttenteAvecResultat;
import com.squareup.picasso.Picasso;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class AjouterArticleActivity extends AppCompatActivity /*implements ActiviteEnAttente*/ {


    EditText et_nomArticle;
    EditText et_reference;
    EditText et_prixArticle;
    private Spinner spinner;
    ImageView imageview;
    String imagepath;
    File sourceFile;
    int totalSize = 0;
    String FILE_UPLOAD_URL = "https://infodb.iutmetz.univ-lorraine.fr/~gaiga4u/ecommerce/upload/UploadToServer.php";
    LinearLayout uploader_area;
    LinearLayout progress_area;
    public DonutProgress donut_progress;
    private static final int REQUEST_WRITE_STORAGE = 112;
    private Article article = null;

    private ActiviteEnAttenteAvecResultat activite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_ajouter_article);


        et_nomArticle = (EditText) findViewById(R.id.et_nomArticle);
        et_reference = (EditText) findViewById(R.id.et_reference);
        et_prixArticle = (EditText) findViewById(R.id.et_prixArticle);
        uploader_area = (LinearLayout) findViewById(R.id.uploader_area);
        progress_area = (LinearLayout) findViewById(R.id.progress_area);
        Button select_button = (Button) findViewById(R.id.button_selectpic);
        Button upload_button = (Button) findViewById(R.id.button_upload);
        donut_progress = (DonutProgress) findViewById(R.id.donut_progress);
        imageview = (ImageView) findViewById(R.id.imageview);
        spinner = (Spinner) findViewById(R.id.list_Categorie);

        // Récupération de la liste de catégorie
        ArrayList<Categorie> temp = CategorieView.getInstance().liste;

        // Definition de l'arrayAdapter pour le spinner
        // Initialization du spinner
        ArrayAdapter spinnerArrayAdapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, temp);
        spinnerArrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerArrayAdapter);

        // Modifier suivant l'activité appelante
        activite = (ActiviteEnAttenteAvecResultat) ArticleView.getInstance();

        Boolean hasPermission = (ContextCompat.checkSelfPermission(AjouterArticleActivity.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED);
        if (!hasPermission) {
            ActivityCompat.requestPermissions(AjouterArticleActivity.this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    REQUEST_WRITE_STORAGE);
        } else {

        }


        // On teste si extra est non null
        if (this.getIntent().getExtras() != null) {
            // S'il n'est pas null, on récupère l'objet à modifier...
            article = (Article) this.getIntent().getSerializableExtra("article");

            // ...et on set les éléments de l'activité
            et_nomArticle.setText(article.getNomArticle());
            et_reference.setText(article.getReference());
            et_prixArticle.setText(String.valueOf(article.getTarif())); // A revoir -> Doit être Float

            // Initialisation du spinner à la valeur de l'article à modifier
            int count = 0;
            int index = 0;
            for (Categorie i : temp) {
                if (i.getIdCateg()==article.getIdCategorie()){
                    index = count;
                    break;
                }
                count++;
            }
            spinner.setSelection(index);
            // On set l'image
            Picasso
                    .with(this)
                    .load("https://infodb.iutmetz.univ-lorraine.fr/~gaiga4u/ecommerce/" + article.getVisuelArticle())
                    .placeholder(R.drawable.ic_close) // can also be a drawable
                    .error(R.drawable.ic_close) // will be displayed if the image cannot be loaded
                    .into(imageview);
        }


        select_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*"); // intent.setType("video/*"); to select videos to upload
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Choisir une image"), 1);
            }
        });


        upload_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (imagepath != null) {
                    // On upload le fichier image sur le serveur
                    new UploadFileToServer().execute();

                    Categorie temp = (Categorie) spinner.getSelectedItem();
                    // Si l'article vaut null, c'est que l'on n'a pas récupéré d'objet article
                    // lors de l'ouverture de l'activité
                    if (article == null) {
                        // C'est une création, nous ne connaissons pas l'id, il vaudra -1
                        article = new Article(
                                -1,
                                et_nomArticle.getText().toString(),
                                et_reference.getText().toString(),
                                Float.parseFloat(et_prixArticle.getText().toString()),
                                sourceFile.getName(),
                                temp.getIdCateg()
                        );
                        Log.i("_aaa", "Création d'un aricle");
                        ArticleDao.getInstance((ActiviteEnAttenteAvecResultat) activite).create(article);
                    } else {
                        article = new Article(
                                // On récupère l'id courant pour modifier le bon article en base
                                article.getIdArticle(),
                                et_nomArticle.getText().toString(),
                                et_reference.getText().toString(),
                                Float.parseFloat(et_prixArticle.getText().toString()),
                                sourceFile.getName(),
                                temp.getIdCateg()
                        );
                        Log.i("_aaa", "Modification d'un aricle");
                        ArticleDao.getInstance((ActiviteEnAttenteAvecResultat) activite).update(article);
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Merci de sélectionner un fichier image", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_WRITE_STORAGE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //reload my activity with permission granted or use the features what required the permission
                } else {
                    Toast.makeText(AjouterArticleActivity.this, "You must give access to storage.", Toast.LENGTH_LONG).show();
                }
            }
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1 && resultCode == RESULT_OK) {

            Uri selectedImageUri = data.getData();
            Log.i("data.getData", String.valueOf(selectedImageUri));
            imagepath = getPath(selectedImageUri);
            Log.i("imagepath", String.valueOf(imagepath));
            BitmapFactory.Options options = new BitmapFactory.Options();
            // down sizing image as it throws OutOfMemory Exception for larger images
            // options.inSampleSize = 10;
            final Bitmap bitmap = BitmapFactory.decodeFile(imagepath, options);
            imageview.setImageBitmap(bitmap);
        }
    }

    public String getPath(Uri uri) {

        String filePath = "";
        // Image pick from recent
        String wholeID = DocumentsContract.getDocumentId(uri);

        // Split at colon, use second item in the array
        String id = wholeID.split(":")[1];

        String[] column = {MediaStore.Images.Media.DATA};

        // where id is equal to
        String sel = MediaStore.Images.Media._ID + "=?";

        Cursor cursor = getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                column, sel, new String[]{id}, null);

        int columnIndex = cursor.getColumnIndex(column[0]);

        if (cursor.moveToFirst()) {
            filePath = cursor.getString(columnIndex);
        }
        cursor.close();
        return filePath;
    }

    private class UploadFileToServer extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            // setting progress bar to zero
            donut_progress.setProgress(0);
            uploader_area.setVisibility(View.GONE); // Making the uploader area screen invisible
            progress_area.setVisibility(View.VISIBLE); // Showing the stylish material progressbar
            sourceFile = new File(imagepath);
            totalSize = (int) sourceFile.length();
            super.onPreExecute();
        }

        @Override
        protected void onProgressUpdate(String... progress) {
            Log.d("PROG", progress[0]);
            donut_progress.setProgress(Integer.parseInt(progress[0])); //Updating progress
        }

        @Override
        protected String doInBackground(String... args) {
            HttpURLConnection.setFollowRedirects(false);
            HttpURLConnection connection = null;
            String fileName = sourceFile.getName();

            try {
                connection = (HttpURLConnection) new URL(FILE_UPLOAD_URL).openConnection();

                connection.setRequestMethod("POST");

                String boundary = "---------------------------boundary";
                String tail = "\r\n--" + boundary + "--\r\n";
                connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
                connection.setDoOutput(true);

                String metadataPart = "--" + boundary + "\r\n"
                        + "Content-Disposition: form-data; name=\"metadata\"\r\n\r\n"
                        + "" + "\r\n";

                String fileHeader1 = "--" + boundary + "\r\n"
                        + "Content-Disposition: form-data; name=\"fileToUpload\"; filename=\""
                        + fileName + "\"\r\n"
                        + "Content-Type: application/octet-stream\r\n"
                        + "Content-Transfer-Encoding: binary\r\n";

                long fileLength = sourceFile.length() + tail.length();
                String fileHeader2 = "Content-length: " + fileLength + "\r\n";
                String fileHeader = fileHeader1 + fileHeader2 + "\r\n";
                String stringData = metadataPart + fileHeader;

                long requestLength = stringData.length() + fileLength;
                connection.setRequestProperty("Content-length", "" + requestLength);
                connection.setFixedLengthStreamingMode((int) requestLength);
                connection.connect();

                DataOutputStream out = new DataOutputStream(connection.getOutputStream());
                out.writeBytes(stringData);
                out.flush();

                int progress = 0;
                int bytesRead = 0;
                byte buf[] = new byte[1024];
                BufferedInputStream bufInput = new BufferedInputStream(new FileInputStream(sourceFile));
                while ((bytesRead = bufInput.read(buf)) != -1) {
                    // write output
                    out.write(buf, 0, bytesRead);
                    out.flush();
                    progress += bytesRead; // Here progress is total uploaded bytes

                    publishProgress("" + (int) ((progress * 100) / totalSize)); // sending progress percent to publishProgress
                }

                // Write closing boundary and close stream
                out.writeBytes(tail);
                out.flush();
                out.close();

                // Get server response
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line = "";
                StringBuilder builder = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    builder.append(line);
                }
                return String.valueOf(builder);
            } catch (Exception e) {
                // Exception
            } finally {
                if (connection != null) connection.disconnect();

            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            Log.e("Response", "Response from server: " + result);
            super.onPostExecute(result);
            finish();
        }

    }

}
