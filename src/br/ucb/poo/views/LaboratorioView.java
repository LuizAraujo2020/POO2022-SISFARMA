package br.ucb.poo.views;

import java.util.ArrayList;

import br.ucb.poo.beans.Laboratorio;
import br.ucb.poo.dao.LaboratorioDAO;

final public class LaboratorioView {
	
	public void listarTodosLaboratorios() {


		ArrayList<Laboratorio> laboratorios = new ArrayList<Laboratorio>();
		LaboratorioDAO dao = new LaboratorioDAO();
		laboratorios = dao.retrieveLaboratorio();
		
		System.out.println("--------------------------");
		System.out.println("ID\tNOME");
		System.out.println("--------------------------");
		for(Laboratorio l : laboratorios ){
			System.out.println(l.getId_laboratorio() + "\t" + l.getNome());
			System.out.println("--------------------------");
		}	
	}
}
