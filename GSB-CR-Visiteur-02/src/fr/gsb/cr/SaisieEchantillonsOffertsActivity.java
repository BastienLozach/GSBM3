package fr.gsb.cr;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import fr.gsb.cr.entites.Medicament;
import fr.gsb.cr.modeles.ModeleGsb;
import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

public class SaisieEchantillonsOffertsActivity extends ActionBarActivity {

	private ListView lvEchantillons ;
	private Map<String, Integer> mapEchantillon = new HashMap<String, Integer>() ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_saisie_echantillons_offerts);
		
		lvEchantillons = (ListView) findViewById(R.id.lvEchantillons);
		ItemAdapteur adapteur = new ItemAdapteur(this) ;
		lvEchantillons.setAdapter (adapteur) ;
		
		//initialisation hashMap
		Bundle bundle = savedInstanceState;
		Set<String> clefs = bundle.keySet();
		for(String clef : clefs){
			int quantite = bundle.getInt(clef) ;
			Medicament medicament = ModeleGsb.getInstance().getMedicament(clef) ;
			mapEchantillon.put(medicament.getDepotLegal(), quantite);
		}
		
		
		/*
		for(Medicament medicament : ModeleGsb.getInstance().getMedicaments()){
			mapEchantillon.put(medicament.getDepotLegal(), 0);
		}
		*/
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.saisie_echantillons_offerts, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	class ItemAdapteur extends ArrayAdapter<Medicament> {

		public ItemAdapteur(Context context) {
			super(context, R.layout.item_echantillons, ModeleGsb.getInstance().getMedicaments());
		}
		
		public View getView(int position, View convertView, ViewGroup parent){
			View ligneItem = convertView ;
			
			if (ligneItem == null){
				LayoutInflater convertisseur = getLayoutInflater() ;
				ligneItem = convertisseur.inflate(R.layout.item_echantillons, parent, false) ;
			}
			
			// nom medicament
			TextView tvEchantillon = (TextView) ligneItem.findViewById(R.id.tvEchantillon) ;
			tvEchantillon.setText( ModeleGsb.getInstance().getMedicaments().get(position).getNomCommercial());
			
			// spinner nombre
			
			Spinner spEchantillon = (Spinner) ligneItem.findViewById(R.id.spEchantillon) ;
			
			String[] listQuantite = {"0","1","2","3","4","5"} ;
			
			ArrayAdapter<String> aaEchantillon = new ArrayAdapter<String>(
															SaisieEchantillonsOffertsActivity.this,
															android.R.layout.simple_spinner_item,
															listQuantite) ;
			aaEchantillon.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item) ;
			
			spEchantillon.setAdapter(aaEchantillon);
			
			spEchantillon.setTag(Integer.valueOf(position));
			
			spEchantillon.setOnItemSelectedListener(
				new AdapterView.OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView<?> parent,
							View view, int position, long id) {
						Integer posMedic = 
								(Integer) 
								(
									(Spinner) view.getParent()
								)
								.getTag();
						
						mapEchantillon.put(ModeleGsb.getInstance().getMedicaments().get(posMedic).getDepotLegal(), position);
						
					}

					@Override
					public void onNothingSelected(AdapterView<?> parent) {
						
						
					}
					
				} 
			);
			
			return ligneItem ;
			
		}
		
	}
	
	public void retour(View vue){
		Intent intentRetour = new Intent() ;
		for(Entry<String, Integer> ligne : mapEchantillon.entrySet()){
		    String key = ligne.getKey();
		    Integer value = ligne.getValue();
			
			intentRetour.putExtra(key, value) ;
		}
		
		setResult(RESULT_OK, intentRetour) ;
		finish() ;
	}
	
}
