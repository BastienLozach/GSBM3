package fr.gsb.cr;

import java.util.GregorianCalendar;

import fr.gsb.cr.entites.RapportVisite;
import fr.gsb.cr.modeles.ModeleGsb;
import fr.gsb.cr.techniques.Session;
import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

public class VisuRapportVisiteActivity extends ActionBarActivity {

	private TextView tvNumRapport ;
	private TextView tvNomPraticien;
	private RapportVisite rapport;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_visu_rapport_visite);
		String matricule = Session.getSession().getLeVisiteur().getMatricule() ;
		int numero = this.getIntent().getIntExtra("numero", 0);
		
		/* test bundle
		Bundle b = this.getIntent().getExtras() ;
		b.keySet()
		*/
		rapport = ModeleGsb.getInstance().getRapportVisiteFromId(numero, matricule) ;
		
		tvNumRapport = (TextView) findViewById(R.id.tvNumRapport);
		tvNomPraticien = (TextView) findViewById(R.id.tvNomPraticien);
		
		tvNumRapport.setText(rapport.getNumero()+"");
		tvNomPraticien.setText(rapport.getLePraticien().getNom() + " " + rapport.getLePraticien().getPrenom());
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.visu_rapport_visite, menu);
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
		Intent intent = new Intent(this, VisuListeRapportsVisitesActivity.class);
		intent.putExtra("mois", rapport.getDateVisite().get(GregorianCalendar.MONTH)+1);
		intent.putExtra("annee", rapport.getDateVisite().get(GregorianCalendar.YEAR));
		startActivity(intent);
	}
}
