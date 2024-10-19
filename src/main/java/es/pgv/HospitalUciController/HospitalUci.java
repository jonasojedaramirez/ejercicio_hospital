package es.pgv.HospitalUciController;

public class HospitalUci {
	
	private  class bloPacientes{
		public int Pacientes = 0;
	}
	private class bloEnfermeros{
		public int Enfermeros = 0;
	}
	private class bloDoctores{
		public int Doctores = 0;
	}
		
	private bloPacientes objbloPaciente = new bloPacientes();
	private bloEnfermeros objbloEnfermeros = new bloEnfermeros();
	private bloDoctores objbloDoctores = new bloDoctores();
	
	
	
	
	private final int MaxNumPacientes = 16;
	
		
	public void EntrarPaciente(String NombrePaciente) {
		
		System.out.println("El " + NombrePaciente + " está esperando a ser ingresado en UCI.");
			synchronized(objbloPaciente) {
				while (!(objbloDoctores.Doctores * 4 > objbloPaciente.Pacientes && objbloEnfermeros.Enfermeros * 2 > objbloPaciente.Pacientes )){
					try {
						objbloPaciente.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				objbloPaciente.Pacientes++;
				System.out.println("El " + NombrePaciente + " ha sido ingresado en UCI. (Pacientes="+objbloPaciente.Pacientes+", Doctores="+objbloDoctores.Doctores+", Enfermeros="+objbloEnfermeros.Enfermeros+")");
				objbloPaciente.notifyAll();
			}
			
	}
	
	public void SalirPaciente(String NombrePaciente) {
		synchronized(objbloPaciente) {	
			objbloPaciente.Pacientes--;
			System.out.println("El " + NombrePaciente + " ha salido de la UCI. (Pacientes="+objbloPaciente.Pacientes+", Doctores="+objbloDoctores.Doctores+", Enfermeros="+objbloEnfermeros.Enfermeros+")");
			objbloPaciente.notifyAll();
		}	
	}
	
	public void EntrarDoctor(String NombreDoctor) {
		System.out.println("El " + NombreDoctor + " está a la espera de entrar al servicio UCI.");
			synchronized(objbloDoctores){
				while (!(objbloDoctores.Doctores * 4 <= objbloPaciente.Pacientes )) {
					try {
						objbloDoctores.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				objbloDoctores.Doctores++;
				System.out.println("El " + NombreDoctor + " ha entrado al servicio UCI. (Pacientes="+objbloPaciente.Pacientes+", Doctores="+objbloDoctores.Doctores+", Enfermeros="+objbloEnfermeros.Enfermeros+")");
				objbloDoctores.notifyAll();
					
				}	
	}
	
	public void SalirDoctor(String NombreDoctor) {
		synchronized(objbloDoctores) {
			objbloDoctores.Doctores--;
			System.out.println("El " + NombreDoctor + " ha abandonado el servicio UCI. (Pacientes="+objbloPaciente.Pacientes+", Doctores="+objbloDoctores.Doctores+", Enfermeros="+objbloEnfermeros.Enfermeros+")");
			objbloDoctores.notifyAll();
		}	
	}
	
	public void EntrarEnfermero(String NombreEnfermero) {
		 System.out.println("El " + NombreEnfermero + " está a la espera de entrar al servicio UCI.");
		 	synchronized(objbloEnfermeros) {
		 		while(!(objbloEnfermeros.Enfermeros * 2 <= objbloPaciente.Pacientes)) {
		 			try {
						objbloEnfermeros.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		 		}
				objbloEnfermeros.Enfermeros++;
				System.out.println("El " + NombreEnfermero + " ha entrado al servicio UCI. (Pacientes="+objbloPaciente.Pacientes+", Doctores="+objbloDoctores.Doctores+", Enfermeros="+objbloEnfermeros.Enfermeros+")");
				objbloEnfermeros.notifyAll();
		 	}	 
	}
	
	public void SalirEnfermero(String NombreEnfermero) {
		synchronized(objbloEnfermeros) {
			objbloEnfermeros.Enfermeros--;
			System.out.println("El " + NombreEnfermero + " ha abandonado el servicio UCI. (Pacientes="+objbloPaciente.Pacientes+", Doctores="+objbloDoctores.Doctores+", Enfermeros="+objbloEnfermeros.Enfermeros+")");
			objbloEnfermeros.notifyAll();
		}	
	}

}
