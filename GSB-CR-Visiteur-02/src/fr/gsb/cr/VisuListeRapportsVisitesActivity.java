package fr.gsb.cr;

import java.util.List;

import fr.gsb.cr.modeles.ModeleGsb;
import fr.gsb.cr.entites.RapportVisite;
import fr.gsb.cr.techniques.Session;
import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


public class VisuListeRapportsVisitesActivity extends ActionBarActivity {

	private TextView tvListeVide ;
	private List<RapportVisite> listeRapportVisite ;
	private ListView lvRapports;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_visu_liste_rapports_visites);
		
		Intent intent = this.getIntent();
		listeRapportVisite = ModeleGsb.getInstance().getRapportsVisites(
				Session.getSession().getLeVisiteur(),
				intent.getIntExtra("mois", 0),
				intent.getIntExtra("annee", 0)
		);
		
		lvRapports = (ListView) findViewById(R.id.lvRapports);
		
		lvRapports.setAdapter(new ArrayAdapter<RapportVisite>(
				this, 
				android.R.layout.simple_list_item_1,
				listeRapportVisite)
		);
		lvRapports.setOnItemClickListener(null);
		lvRapports.setOnItemClickListener(
				
			new OnItemClickListener() {
			
				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					RapportVisite rapport = listeRapportVisite.get(position);
					
					Intent intent = new Intent(VisuListeRapportsVisitesActivity.this, VisuRapportVisiteActivity.class);
					intent.putExtra("numero", rapport.getNumero());
					startActivity(intent);
					
				}
			}
		);
		
		tvListeVide = (TextView) findViewById(R.id.tvListeVide);
		if(listeRapportVisite.size() == 0){
			tvListeVide.setText("Aucun rapports pour ce mois");
		}
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.visu_liste_rapports_visites, menu);
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
		
		Intent intent = new Intent(this, RechercherRapportVisiteActivity.class);
		startActivity(intent);
	}
}
