package fr.gsb.cr;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Set;
import java.util.Map.Entry;

import fr.gsb.cr.entites.Medicament;
import fr.gsb.cr.entites.Motif;
import fr.gsb.cr.entites.Praticien;
import fr.gsb.cr.entites.RapportVisite;
import fr.gsb.cr.entites.Visiteur;
import fr.gsb.cr.modeles.ModeleGsb;
import fr.gsb.cr.techniques.Session;
import android.support.v7.app.ActionBarActivity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class SaisieRapportVisiteActivity extends ActionBarActivity {
	//rapport
	private RapportVisite rapport = new RapportVisite();
	
	//bilan
	private EditText etBilan ;
	
	//Date Visite
	private TextView tvDateVisite ;
	private GregorianCalendar date = new GregorianCalendar() ;
	
	//Spinners
	Spinner spPraticien ;
	Spinner spMotif ;
	Spinner spCoefConfiance ;
	
	//listener DatePicker
	private DatePickerDialog.OnDateSetListener ecouteurDate = 
			new DatePickerDialog.OnDateSetListener() {
				
				@Override
				public void onDateSet(DatePicker view, int year, int monthOfYear,
						int dayOfMonth) {
					// TODO Auto-generated method stub
					String dateString = String.format(
							"%02d/%02d/%04d",
							dayOfMonth,
							monthOfYear + 1,
							year
							);
					tvDateVisite.setText(dateString);
					date = new GregorianCalendar(
							year,
							monthOfYear,
							dayOfMonth
							);
					
				}
			};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_saisie_rapport_visite);
		//bilan
		etBilan = (EditText) findViewById(R.id.etBilan);
		
		//dateVisite
		tvDateVisite = (TextView) findViewById(R.id.tvDateVisite);
		int jour = date.get(Calendar.DAY_OF_MONTH);
		int mois = date.get(Calendar.MONTH);
		int annee = date.get(Calendar.YEAR);
		String dateString = String.format(
				"%02d/%02d/%04d",
				jour,
				mois + 1,
				annee
				);		
		tvDateVisite.setText(dateString) ;
		
		//Spinners
		
			//spinner praticien
		spPraticien = (Spinner) findViewById(R.id.spPraticien) ;
		spPraticien.setOnItemSelectedListener(
					new AdapterView.OnItemSelectedListener() {

						@Override
						public void onItemSelected(AdapterView<?> parent,
								View view, int position, long id) {
							ModeleGsb.getInstance().getLesPraticiens().get(position);
						}

						@Override
						public void onNothingSelected(AdapterView<?> parent) {
							
							
						}
					}
				
				
				) ;
		
		
		ArrayAdapter<Praticien> aaPraticien = new ArrayAdapter<Praticien>(
				this,
				android.R.layout.simple_spinner_item,
				ModeleGsb.getInstance().getLesPraticiens()
				);
		
		aaPraticien.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item) ;
		spPraticien.setAdapter(aaPraticien);
		
			//spinner Motif
		spMotif = (Spinner) findViewById(R.id.spMotif) ;
		spMotif.setOnItemSelectedListener(
					new AdapterView.OnItemSelectedListener() {
	
						@Override
						public void onItemSelected(AdapterView<?> parent,
								View view, int position, long id) {
							 
							ModeleGsb.getInstance().getLesMotifs().get(position);
						}
	
						@Override
						public void onNothingSelected(AdapterView<?> parent) {
							
						}
					}
				
				
				) ;
		
		
		ArrayAdapter<Motif> aaMotif = new ArrayAdapter<Motif>(
				this,
				android.R.layout.simple_spinner_item,
				ModeleGsb.getInstance().getLesMotifs()
				);
		
		aaMotif.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item) ;
		spMotif.setAdapter(aaMotif);
		
		
			//spinner coef
		spCoefConfiance = (Spinner) findViewById(R.id.spCoefConfiance) ;
		spCoefConfiance.setOnItemSelectedListener(
					new AdapterView.OnItemSelectedListener() {
	
						@Override
						public void onItemSelected(AdapterView<?> parent,
								View view, int position, long id) {
							
							ModeleGsb.getInstance().getLesCoef().get(position);
						}
	
						@Override
						public void onNothingSelected(AdapterView<?> parent) {
							
							
						}
					}
				
				
				) ;
		
		
		ArrayAdapter<Integer> aaCoefConfiance = new ArrayAdapter<Integer>(
				this,
				android.R.layout.simple_spinner_item,
				ModeleGsb.getInstance().getLesCoef()
				);
		
		aaCoefConfiance.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item) ;
		spCoefConfiance.setAdapter(aaCoefConfiance);
		
		//Bundle
		
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.saisie_rapport_visite, menu);
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
	
	public void changerDate(View view){
		
		int jour = date.get(Calendar.DAY_OF_MONTH);
		int mois = date.get(Calendar.MONTH);
		int annee = date.get(Calendar.YEAR);
		
		new DatePickerDialog(this,ecouteurDate, annee, mois, jour).show() ;
					
	}
	
	public void valider (View view){
		
		
						
		rapport.setLePraticien((Praticien)spPraticien.getSelectedItem());
		rapport.setLeMotif((Motif)spMotif.getSelectedItem());
		rapport.setCoefConfiance((Integer)spCoefConfiance.getSelectedItem());
		rapport.setDateVisite(date) ;
		rapport.setBilan(etBilan.getText().toString());
		rapport.setLeVisiteur(Session.getSession().getLeVisiteur());
		
		rapport.setDateRedaction(new GregorianCalendar());
		rapport.setLu(false);
		
		ModeleGsb.getInstance().enregistrerRapportVisite(rapport);
		
		Intent intent = new Intent(this, MenuGsbActivity.class);
		startActivity(intent);
		
	}
	
	public void echantillon(View view){
		if(rapport.getLesEchantillons() == null){
			rapport.setLesEchantillons(new HashMap<Medicament, Integer>()) ;
			for(Medicament medicament : ModeleGsb.getInstance().getMedicaments()){
				rapport.getLesEchantillons().put(medicament, 0);
			}
		}
		Intent intent = new Intent(this, SaisieEchantillonsOffertsActivity.class);
		for(Entry<Medicament, Integer> ligne : rapport.getLesEchantillons().entrySet()){
		    String key = ligne.getKey().getDepotLegal();
		    Integer value = ligne.getValue();
			
			intent.putExtra(key, value) ;
		}
		startActivityForResult(intent, 42);

	}
	
	public void onActivityResult(int requestCode, int resultCode, Intent data){
		super.onActivityResult(requestCode, resultCode, data);
		
		if (requestCode == 42){
			if(resultCode == RESULT_OK){
				rapport.setLesEchantillons(new HashMap<Medicament, Integer>()) ;
				
				Bundle bundle = data.getExtras() ;
				Set<String> clefs = bundle.keySet();
				for(String clef : clefs){
					int quantite = bundle.getInt(clef) ;
					Medicament medicament = ModeleGsb.getInstance().getMedicament(clef) ;
					rapport.getLesEchantillons().put(medicament, quantite);
				}
				
			}
		}
	}
	
}
