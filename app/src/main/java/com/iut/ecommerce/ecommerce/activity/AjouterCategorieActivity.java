package com.iut.ecommerce.ecommerce.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.github.lzyzsd.circleprogress.DonutProgress;
import com.iut.ecommerce.ecommerce.R;
import com.iut.ecommerce.ecommerce.dao.ArticleDao;
import com.iut.ecommerce.ecommerce.dao.CategorieDao;
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

public class AjouterCategorieActivity extends AppCompatActivity /*implements View.OnClickListener */{

    EditText et_nomCategorie;
    ImageView imageview;
    String imagepath;
    File sourceFile;
    int totalSize = 0;
    String FILE_UPLOAD_URL = "https://infodb.iutmetz.univ-lorraine.fr/~gaiga4u/ecommerce/upload/UploadToServer.php";
    LinearLayout uploader_area;
    LinearLayout progress_area;
    public DonutProgress donut_progress;
    private static final int REQUEST_WRITE_STORAGE = 112;
    private Categorie categorie = null;

    private ActiviteEnAttenteAvecResultat activite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_ajouter_categorie);

        et_nomCategorie = (EditText) findViewById(R.id.et_nomCategorie);
        uploader_area = (LinearLayout) findViewById(R.id.uploader_area);
        progress_area = (LinearLayout) findViewById(R.id.progress_area);
        Button select_button = (Button) findViewById(R.id.button_selectpic);
        Button upload_button = (Button) findViewById(R.id.button_upload);
        donut_progress = (DonutProgress) findViewById(R.id.donut_progress);
        imageview = (ImageView) findViewById(R.id.imageview);


        // Modifier suivant l'activité appelante
        activite = (ActiviteEnAttenteAvecResultat) CategorieView.getInstance();

        Boolean hasPermission = (ContextCompat.checkSelfPermission(AjouterCategorieActivity.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED);
        if (!hasPermission) {
            ActivityCompat.requestPermissions(AjouterCategorieActivity.this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    REQUEST_WRITE_STORAGE);
        }else {

        }

        // On teste si extra est non null
        if (this.getIntent().getExtras() != null) {
            // S'il n'est pas null, on récupère l'objet à modifier...
            categorie = (Categorie) this.getIntent().getSerializableExtra("categorie");

            // ...et on set les éléments de l'activité
            et_nomCategorie.setText(categorie.getNomCateg());
            // On set l'image
            Picasso
                    .with(this)
                    .load("https://infodb.iutmetz.univ-lorraine.fr/~gaiga4u/ecommerce/" + categorie.getVisuelCateg())
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

                // On vérifie dans un premier temps que le formulaire est bien rempli
                if (et_nomCategorie.getText().toString().isEmpty()) {
                    // On défini une bordure
                    ShapeDrawable shape = new ShapeDrawable(new RectShape());
                    shape.getPaint().setColor(Color.RED);
                    shape.getPaint().setStyle(Paint.Style.STROKE);
                    shape.getPaint().setStrokeWidth(3);

                    Toast.makeText(getApplicationContext(), "Merci de remplir toutes les informations", Toast.LENGTH_SHORT).show();
                    // On applique la bordure
                    et_nomCategorie.setBackground(shape);
                // Si c'est bon, on peut tenter d'ajouter les infos à la BDD
                } else {
                    // Log.i("_aca", "Valeur de la catégorie : "+categorie.toString());
                    if (imagepath != null && categorie == null) {
                        Log.i("_aca", "Cas 1 : imagepath non null et categorie null");
                        // On upload le fichier image sur le serveur
                        new UploadFileToServer().execute();

                        // On crée la référence dans la base de données
                        Categorie categorie = new Categorie(
                                -1,
                                et_nomCategorie.getText().toString(),
                                sourceFile.getName());
                        Log.i("_aca", "Création d'une catégorie");
                        CategorieDao.getInstance((ActiviteEnAttenteAvecResultat) activite).create(categorie);

                    }else if ((imagepath == null && categorie != null)  || (imagepath != null && categorie != null)){
                        Log.i("_aca", "Cas 2 : imagepath null ou non null et categorie non null");

                        if(imagepath!=null){
                            // On upload le fichier image sur le serveur
                            new UploadFileToServer().execute();

                            categorie = new Categorie(
                                    // On récupère l'id courant pour modifier le bon article en base
                                    categorie.getIdCateg(),
                                    et_nomCategorie.getText().toString(),
                                    sourceFile.getName()
                            );
                        } else {
                            categorie = new Categorie(
                                    // On récupère l'id courant pour modifier le bon article en base
                                    categorie.getIdCateg(),
                                    et_nomCategorie.getText().toString(),
                                    categorie.getVisuelCateg());
                            finish();
                        }

                        Log.i("_aca", "Modification d'une catégorie");
                        CategorieDao.getInstance((ActiviteEnAttenteAvecResultat) activite).update(categorie);
                    } else {
                        Log.i("_aca", "Cas 3 : imagepath null et categorie null");
                        Toast.makeText(getApplicationContext(), "Merci de sélectionner un fichier image", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode)
        {
            case REQUEST_WRITE_STORAGE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    //reload my activity with permission granted or use the features what required the permission
                } else
                {
                    Toast.makeText(AjouterCategorieActivity.this, "You must give access to storage.", Toast.LENGTH_LONG).show();
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
            totalSize = (int)sourceFile.length();
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

                    publishProgress(""+(int)((progress*100)/totalSize)); // sending progress percent to publishProgress
                }

                // Write closing boundary and close stream
                out.writeBytes(tail);
                out.flush();
                out.close();

                // Get server response
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line = "";
                StringBuilder builder = new StringBuilder();
                while((line = reader.readLine()) != null) {
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
