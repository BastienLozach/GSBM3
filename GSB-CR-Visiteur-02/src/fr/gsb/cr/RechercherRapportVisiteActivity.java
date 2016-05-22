package fr.gsb.cr;

import java.util.ArrayList;
import java.util.List;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class RechercherRapportVisiteActivity extends ActionBarActivity implements AdapterView.OnItemSelectedListener {

	private Spinner spMois ;
	private Spinner spAnnee ;
	
	private static final List<Integer> moisList = new ArrayList<Integer>() ;
	private static final List<Integer> AnneeList = new ArrayList<Integer>() ;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rechercher_rapport_visite);
		
		//listes
		for(int i = 1; i <= 12; i+=1){
			moisList.add(i);
		}
		for(int i = 2010; i <= 2016; i+=1){
			AnneeList.add(i);
		}
		
		//spMois
		spMois = (Spinner) findViewById(R.id.spMois);
		spMois.setOnItemSelectedListener(this);
		ArrayAdapter<Integer> aaMois = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item, moisList);
		aaMois.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spMois.setAdapter(aaMois);
			
		
		//spAnnee
		spAnnee = (Spinner) findViewById(R.id.spAnnee);
		spAnnee.setOnItemSelectedListener(this);
		ArrayAdapter<Integer> aaAnnee = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item, AnneeList);
		aaAnnee.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spAnnee.setAdapter(aaAnnee);
		
		
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.rechercher_rapport_visite, menu);
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
	
	
	public void retour(View view){
		
		Intent intent = new Intent(this, MenuGsbActivity.class);
		startActivity(intent);
	}
	
	public void afficher(View view){
		Intent intent = new Intent(this, VisuListeRapportsVisitesActivity.class);
		intent.putExtra("mois", moisList.get(spMois.getSelectedItemPosition()));
		intent.putExtra("annee", AnneeList.get(spAnnee.getSelectedItemPosition()));
		startActivity(intent);
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub
		
	}
}
