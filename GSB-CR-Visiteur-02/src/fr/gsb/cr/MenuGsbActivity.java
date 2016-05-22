package fr.gsb.cr;

import fr.gsb.cr.entites.Visiteur;
import fr.gsb.cr.techniques.Session;
import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MenuGsbActivity extends ActionBarActivity {

	TextView tvPrenom ;
	TextView tvNom ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu_gsb);
		
		tvPrenom = (TextView) findViewById(R.id.tvPrenom);
		tvNom = (TextView) findViewById(R.id.tvNom);
		Visiteur visiteur = Session.getSession().getLeVisiteur();		
		tvNom.setText(visiteur.getNom());
		tvPrenom.setText(visiteur.getPrenom());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_gsb, menu);
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
	
	public void deconnecter(View view){
		Session.fermer();
			
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
	}
	
	public void consulter(View view){
					
		Intent intent = new Intent(this, RechercherRapportVisiteActivity.class);
		startActivity(intent);
	}
	
	public void saisir(View view){
		
		Intent intent = new Intent(this, SaisieRapportVisiteActivity.class);
		startActivity(intent);
	}
	
	
}
