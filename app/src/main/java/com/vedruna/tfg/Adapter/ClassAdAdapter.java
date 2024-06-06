package com.vedruna.tfg.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.vedruna.tfg.DTO.ClassAdDTO;
import com.vedruna.tfg.Model.ClassAd;
import com.vedruna.tfg.R;

import java.util.List;

/**
 * Este adaptador se utiliza para mostrar la información de los anuncios de clases en una lista.
 */
public class ClassAdAdapter extends BaseAdapter {

    List<ClassAdDTO> classAds;
    Context context;

    /**
     * Constructor del adaptador.
     * @param context El contexto de la aplicación.
     * @param classAds La lista de objetos ClassAd a mostrar en la lista.
     */
    public ClassAdAdapter(Context context, List<ClassAdDTO> classAds) {
        this.context = context;
        this.classAds = classAds;
    }

    @Override
    public int getCount() {
        return classAds.size();
    }

    @Override
    public Object getItem(int position) {
        return classAds.get(position);
    }

    @Override
    public long getItemId(int position) {
        return classAds.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            // Inflar el diseño de la vista de la lista si es nulo
            convertView = LayoutInflater.from(context).inflate(R.layout.class_ad_list_item, parent, false);

            // Crear un nuevo ViewHolder y asignar las vistas correspondientes
            viewHolder = new ViewHolder();
            viewHolder.titleText = convertView.findViewById(R.id.titleText);
            viewHolder.descriptionText = convertView.findViewById(R.id.descriptionText);
            viewHolder.priceText = convertView.findViewById(R.id.priceText);

            // Establecer el ViewHolder como una etiqueta para la vista convertida
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // Obtén el anuncio actual
        ClassAdDTO classAd = (ClassAdDTO) getItem(position);

        // Asignar los valores del anuncio a las vistas
        viewHolder.titleText.setText(classAd.getTitle());
        viewHolder.descriptionText.setText(classAd.getDescription());
        viewHolder.priceText.setText(String.format("€%.2f", classAd.getPrice()));

       // Picasso.get().load(classAd.getUser().getIdAvatar()).into(viewHolder.imageView);

        return convertView;
    }

    /**
     * Clase interna ViewHolder que contiene las vistas de cada elemento de la lista.
     */
    static class ViewHolder {
        TextView titleText;
        TextView descriptionText;
        TextView priceText;
    }
}

