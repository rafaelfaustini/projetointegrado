package projetointegrado.com.conversormedidas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MedidasAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<String> valores;
    private ArrayList<String> unidades;
    public MedidasAdapter(Context context, ArrayList<String> valores, ArrayList<String> unidades){
        this.context = context;
        this.valores = valores;
        this.unidades = unidades;
    }
    @Override
    public int getCount() {
        return valores.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.adapter_view_layout, null);

        TextView textView1 = (TextView)convertView.findViewById(R.id.valor);
        TextView textView2 = (TextView)convertView.findViewById(R.id.unidade);
        textView1.setText(valores.get(position));
        textView2.setText(unidades.get(position));
        return convertView;
    }
}
