package com.example.applicationtp;


import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.pm.ShortcutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.text.AllCapsTransformationMethod;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainAdapter extends FirebaseRecyclerAdapter <MainModel,MainAdapter.myViewHolder>{

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */


    public MainAdapter(@NonNull FirebaseRecyclerOptions<MainModel> options) {
        super(options);
    }




    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, final int position, @NonNull MainModel model) {

        holder.marque.setText(model.getMarque());
        holder.etat.setText(model.getEtat());
        holder.localisation.setText(model.getLocalisation());
        holder.panne.setText(model.getPanne());
        holder.matricule.setText(model.getMatricule());

        Glide.with(holder.img.getContext())
                .load(model.getImg())
                .placeholder(R.drawable.common_google_signin_btn_icon_dark)
                .error(R.drawable.common_google_signin_btn_icon_dark_normal)
                .into(holder.img);

        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.img.getContext())
                        .setContentHolder(new ViewHolder(R.layout.update_popup))
                        .setExpanded(true,1200)
                        .create();
                //dialogPlus.show();

                View view = dialogPlus.getHolderView();

                EditText marque = view.findViewById(R.id.nametext);
                EditText etat = view.findViewById(R.id.nametext2);
                EditText localisation = view.findViewById(R.id.nametext3);
                EditText panne = view.findViewById(R.id.nametext4);
                EditText matricule = view.findViewById(R.id.nametext5);
                EditText img = view.findViewById(R.id.urlimg);

                Button btnUpdate = view.findViewById(R.id.btnUpdate);

                marque.setText(model.getMarque());
                etat.setText(model.getEtat());
                localisation.setText(model.getLocalisation());
                panne.setText(model.getPanne());
                matricule.setText(model.getMatricule());
                img.setText(model.getImg());

                dialogPlus.show();

                btnUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Map<String,Object> map = new HashMap<>();
                        map.put("marque",marque.getText().toString());
                        map.put("etat",etat.getText().toString());
                        map.put("localisation",localisation.getText().toString());
                        map.put("panne",panne.getText().toString());
                        map.put("matricule",matricule.getText().toString());
                        map.put("img",img.getText().toString());

                        FirebaseDatabase.getInstance().getReference().child("car")
                                .child(getRef(position).getKey()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(holder.marque.getContext(),"data Updated Successfully.", Toast.LENGTH_SHORT).show();
                                       dialogPlus.dismiss();

                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(holder.marque.getContext(),"Error while Updating.", Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();

                                    }
                                });






                    }
                });





            }
        });

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.marque.getContext());
                builder.setTitle("are you sure??");
                builder.setMessage("deleted data!!");

                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FirebaseDatabase.getInstance().getReference().child("car")
                                .child(getRef(position).getKey()).removeValue();

                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(holder.marque.getContext(),"Cancelled",Toast.LENGTH_SHORT).show();

                    }
                });
                builder.show();
            }


        });



    }





    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_item,parent,false);
        return new myViewHolder(view);
    }







    class myViewHolder extends RecyclerView.ViewHolder{

        CircleImageView img;
        TextView etat,localisation,marque,matricule,panne;

        Button btnEdit,btnDelete;


        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            img = (CircleImageView)itemView.findViewById(R.id.img1);
            marque = (TextView)itemView.findViewById(R.id.nametext);
            etat = (TextView)itemView.findViewById(R.id.nametext2);
            localisation = (TextView)itemView.findViewById(R.id.nametext3);
            panne = (TextView)itemView.findViewById(R.id.nametext4);
            matricule = (TextView)itemView.findViewById(R.id.nametext5);

            btnEdit = (Button)itemView.findViewById(R.id.btnEdit);
            btnDelete = (Button)itemView.findViewById(R.id.btnDelete);


        }
    }
}
