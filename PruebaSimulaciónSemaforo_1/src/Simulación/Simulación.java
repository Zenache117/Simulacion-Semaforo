/*
 * 
 * ----------------------------------------------------Mauricio Tamez Rodriguez 1811848 ITS Lab Sistemas Adaptativos------------------------------------------------------------
 *-----------------------------------------------------Simulación de Semaforo en un cruce de calles con paso peatonal-----------------------------------------------------------
 *----------------------------------------Se elige los carriles con mayor acumulación de coches y se les da prioridad para ceder el paso----------------------------------------
 */
package Simulación;

import java.awt.EventQueue;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;


public class Simulación {
	
	static int Semaforo_Arriba;
	static int Semaforo_Abajo;
	static int Semaforo_Izquierda;
	static int Semaforo_Derecha;
	

	private JFrame frame;

	JLabel RojoArriba = new JLabel("");
	JLabel RojoAbajo = new JLabel("");
	JLabel RojoDerecho = new JLabel("");
	JLabel RojoIzquierda = new JLabel("");
	
	JLabel SupIzqHor = new JLabel("");
	JLabel SupIzqVer = new JLabel("");
	JLabel SupDerHor = new JLabel("");
	JLabel SupDerVer = new JLabel("");
	JLabel InfIzqHor = new JLabel("");
	JLabel InfIzqVer = new JLabel("");
	JLabel InfDerHor = new JLabel("");
	JLabel InfDerVer = new JLabel("");
	
	JLabel AutosCarrilSup = new JLabel("");
	JLabel AutosCarrilIzq = new JLabel("");
	JLabel AutosCarrilInf = new JLabel("");
	JLabel AutosCarrilDer = new JLabel("");
	
	static int CarroCalleArriba, CarroCalleAbajo, CarroCalleDerecha, CarroCalleIzquierda;
	
	Image imagen_rojo = new ImageIcon(this.getClass().getResource("/CirculoRojo2.png")).getImage();
	Image imagen_verde = new ImageIcon(this.getClass().getResource("/CirculoVerde2.png")).getImage();
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Simulación window = new Simulación();
					window.frame.setVisible(true);
					
					
					
					
					int Lapso = 4; //Lapso de tiempo promedio de cambio de imagen en los semaforos en segundos
					
					Timer tiempo;
					TimerTask Cambio_imagen;
					
					int Lapso_Transformado = Lapso*1000;
					
					
					CarroCalleArriba = Aleatorio();
					CarroCalleAbajo = Aleatorio();
					CarroCalleDerecha = Aleatorio();
					CarroCalleIzquierda = Aleatorio();
					
					
					
					Cambio_imagen = new TimerTask() {
						@Override
						public void run (){
							
							
							int Diferencia = (CarroCalleArriba + CarroCalleAbajo) - (CarroCalleIzquierda + CarroCalleDerecha);
							Image imagen_rojo2 = new ImageIcon(this.getClass().getResource("/CirculoRojo2.png")).getImage();
							Image imagen_verde2 = new ImageIcon(this.getClass().getResource("/CirculoVerde2.png")).getImage();
							Image imagen_flecha_izquierda = new ImageIcon(this.getClass().getResource("/FlechaAzulIzquierda.png")).getImage();
							Image imagen_flecha_derecha = new ImageIcon(this.getClass().getResource("/FlechaAzulDerecha.png")).getImage();
							Image imagen_flecha_arriba = new ImageIcon(this.getClass().getResource("/FlechaAzulArriba.png")).getImage();
							Image imagen_flecha_abajo = new ImageIcon(this.getClass().getResource("/FlechaAzulAbajo.png")).getImage();
							Image imagen_verde = new ImageIcon(this.getClass().getResource("/Verde.png")).getImage();
							
							
						
							window.AutosCarrilSup.setText(Integer.toString(CarroCalleArriba)+"Autos");
							window.AutosCarrilInf.setText(Integer.toString(CarroCalleAbajo)+"Autos");
							window.AutosCarrilDer.setText(Integer.toString(CarroCalleDerecha)+"Autos");
							window.AutosCarrilIzq.setText(Integer.toString(CarroCalleIzquierda)+"Autos");
							
/*
 * Aqui se selecciona la diferencia entre los carriles para saber cual es el que tiene preferencia a la luz verde
*/
							if(Diferencia>=0) {
						     Semaforo_Arriba = 0;
							 Semaforo_Abajo = 0;
							 Semaforo_Izquierda = 1;
						     Semaforo_Derecha = 1;
							}
							else {
							 Semaforo_Arriba = 1;
							 Semaforo_Abajo = 1;
							 Semaforo_Izquierda = 0;
							 Semaforo_Derecha = 0;
							}
/*
 * En caso de que un semaforo se encuentre en rojo su siguiente cambio es el verde, de manera visceversa tambien, y se ve declarado mediante los dos siguientes switch 
 */
							switch(Semaforo_Arriba) {
							case 0:Semaforo_Arriba=1;
							window.RojoArriba.setIcon(new ImageIcon(imagen_verde2));
							window.RojoAbajo.setIcon(new ImageIcon(imagen_verde2));	
							if(CarroCalleArriba>=4 && CarroCalleAbajo>=4 && CarroCalleArriba<=8 && CarroCalleAbajo<=8) {
							CarroCalleArriba = CarroCalleArriba - 4;
							CarroCalleAbajo = CarroCalleAbajo - 4;
						
							}
/*
 *En caso de que una calle se este congestionando demasiado se le da prioridad, pero para no hacer el programa tedioso con una espera del tiempo que se tarda cada auto en cruzar
 *se le dio preferencia a un metodo donde se expresa que a este carril se le esperara hasta que crucen la mayoria de sus coches mediante una resta de una cantidad considerable
 *del contenido neto del carril 
 */
							else if(CarroCalleArriba>=9){
								CarroCalleArriba = CarroCalleArriba - 8;
							}
							else if(CarroCalleAbajo>=9){
								CarroCalleAbajo = CarroCalleAbajo - 8;
							}
							else {
								CarroCalleArriba = CarroCalleArriba - CarroCalleArriba;
								CarroCalleAbajo = CarroCalleAbajo - CarroCalleAbajo;

							}
/*
 * Aqui se genera un cambio de imagenes dentro de los labels simulando la figura del semaforo 
 */
							window.SupIzqVer.setIcon(new ImageIcon(imagen_flecha_abajo));
							window.SupDerVer.setIcon(new ImageIcon(imagen_flecha_abajo));
							window.InfDerVer.setIcon(new ImageIcon(imagen_flecha_arriba));
							window.InfIzqVer.setIcon(new ImageIcon(imagen_flecha_arriba));
							break;
							case 1: Semaforo_Arriba=0;
							window.RojoArriba.setIcon(new ImageIcon(imagen_rojo2));
							window.RojoAbajo.setIcon(new ImageIcon(imagen_rojo2));
							window.SupIzqHor.setIcon(new ImageIcon(imagen_flecha_derecha));
							window.SupDerHor.setIcon(new ImageIcon(imagen_flecha_izquierda));
							window.InfDerHor.setIcon(new ImageIcon(imagen_flecha_izquierda));
							window.InfIzqHor.setIcon(new ImageIcon(imagen_flecha_derecha));
							break;
							}
							switch(Semaforo_Derecha) {
							case 0: Semaforo_Derecha=1;
							window.RojoDerecho.setIcon(new ImageIcon(imagen_verde2));
							window.RojoIzquierda.setIcon(new ImageIcon(imagen_verde2));
							if(CarroCalleDerecha>=4 && CarroCalleDerecha>=4 && CarroCalleDerecha<=8 && CarroCalleDerecha<=8) {
								CarroCalleDerecha = CarroCalleDerecha - 4;
								CarroCalleIzquierda = CarroCalleIzquierda - 4;
								}
							else if(CarroCalleDerecha>=9){
								CarroCalleDerecha = CarroCalleDerecha - 8;
							}
							else if(CarroCalleIzquierda>=9){
								CarroCalleIzquierda = CarroCalleIzquierda - 8;
							}
								else {
									CarroCalleDerecha = CarroCalleDerecha - CarroCalleDerecha;
									CarroCalleIzquierda = CarroCalleIzquierda - CarroCalleIzquierda;

								}
							window.SupIzqVer.setIcon(new ImageIcon(imagen_verde));
							window.SupDerVer.setIcon(new ImageIcon(imagen_verde));
							window.InfDerVer.setIcon(new ImageIcon(imagen_verde));
							window.InfIzqVer.setIcon(new ImageIcon(imagen_verde));
								break;
						
							case 1: Semaforo_Derecha=0;
							window.RojoDerecho.setIcon(new ImageIcon(imagen_rojo2));
							window.RojoIzquierda.setIcon(new ImageIcon(imagen_rojo2));
							window.SupIzqHor.setIcon(new ImageIcon(imagen_verde));
							window.SupDerHor.setIcon(new ImageIcon(imagen_verde));
							window.InfDerHor.setIcon(new ImageIcon(imagen_verde));
							window.InfIzqHor.setIcon(new ImageIcon(imagen_verde));
							break;
							}
							
/*
 *Se simula que mientras esperan el cambio de luz o inculive cuando estan en ella, vienen mas coches a cada carril llegando a la cola 
 */
							CarroCalleArriba = CarroCalleArriba + Aleatorio();
							CarroCalleAbajo = CarroCalleAbajo + Aleatorio();
							CarroCalleDerecha = CarroCalleDerecha + Aleatorio();
							CarroCalleIzquierda = CarroCalleIzquierda + Aleatorio();
						}
						
						
					};
					
					tiempo = new Timer();
					tiempo.schedule(Cambio_imagen, 1000 , Lapso_Transformado);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		
	}

	
	public Simulación() {
		Initialize();
	}
	
/*
 *Aqui se genera la pantalla que fue echa con ayuda del prlugin "windows builder" de la aplicación "eclipse y despues se edito para conveniencia del resto del codigo.
 *Se utilizaron paneles para simular entorno y labels con imagenes de iconos para representar figuras o anuncios 
 */
	private void Initialize() {
		
		Image imagen_peaton = new ImageIcon(this.getClass().getResource("/Peaton2.png")).getImage();
		
		
		frame = new JFrame();
		frame.setBounds(100, 100, 893, 592);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		JPanel panelSupIzq = new JPanel();
		panelSupIzq.setBackground(Color.GREEN);
		panelSupIzq.setBounds(10, 11, 288, 211);
		frame.getContentPane().add(panelSupIzq);
		panelSupIzq.setLayout(null);
		
		JLabel LabelSupIzq = new JLabel("");
		LabelSupIzq.setBounds(10, 11, 172, 117);
		panelSupIzq.add(LabelSupIzq);
		LabelSupIzq.setIcon(new ImageIcon(imagen_peaton));
		
		
		SupIzqHor.setBounds(10, 139, 150, 50);
		panelSupIzq.add(SupIzqHor);
		SupIzqVer.setBounds(192, 11, 50, 150);
		panelSupIzq.add(SupIzqVer);
		
		
		JPanel panelSupDer = new JPanel();
		panelSupDer.setBackground(Color.GREEN);
		panelSupDer.setBounds(579, 11, 288, 211);
		frame.getContentPane().add(panelSupDer);
		panelSupDer.setLayout(null);
		
		
		JLabel labelSupDer = new JLabel("");
		labelSupDer.setBounds(106, 11, 172, 117);
		panelSupDer.add(labelSupDer);
		labelSupDer.setIcon(new ImageIcon(imagen_peaton));
		
		
		SupDerHor.setBounds(129, 139, 150, 50);
		panelSupDer.add(SupDerHor);
		SupDerVer.setBounds(46, 11, 50, 150);
		panelSupDer.add(SupDerVer);
		
		
		JPanel panelInfIzq = new JPanel();
		panelInfIzq.setBackground(Color.GREEN);
		panelInfIzq.setBounds(10, 331, 288, 211);
		frame.getContentPane().add(panelInfIzq);
		panelInfIzq.setLayout(null);
		
		JLabel LabelInfIzq = new JLabel("");
		LabelInfIzq.setBounds(10, 86, 172, 117);
		panelInfIzq.add(LabelInfIzq);
		LabelInfIzq.setIcon(new ImageIcon(imagen_peaton));
		
		JPanel panelInfDer = new JPanel();
		panelInfDer.setBackground(Color.GREEN);
		panelInfDer.setBounds(579, 331, 288, 211);
		frame.getContentPane().add(panelInfDer);
		panelInfDer.setLayout(null);
		
		JLabel LabelInfDer = new JLabel("");
		LabelInfDer.setBounds(106, 83, 172, 117);
		panelInfDer.add(LabelInfDer);
		panelInfIzq.add(LabelInfIzq);
		
		
		InfIzqHor.setBounds(10, 11, 150, 50);
		panelInfIzq.add(InfIzqHor);
		InfIzqVer.setBounds(189, 53, 50, 150);
		panelInfIzq.add(InfIzqVer);
		LabelInfDer.setIcon(new ImageIcon(imagen_peaton));
		InfDerHor.setBounds(106, 11, 150, 50);
		panelInfDer.add(InfDerHor);
		InfDerVer.setBounds(46, 50, 50, 150);
		panelInfDer.add(InfDerVer);
		
		JPanel SemDerecha = new JPanel();
		SemDerecha.setBackground(Color.DARK_GRAY);
		SemDerecha.setBounds(579, 233, 34, 87);
		frame.getContentPane().add(SemDerecha);
		SemDerecha.setLayout(null);
		
		JPanel SemArriba = new JPanel();
		SemArriba.setBackground(Color.DARK_GRAY);
		SemArriba.setBounds(416, 151, 34, 71);
		frame.getContentPane().add(SemArriba);
		SemArriba.setLayout(null);
		
		JPanel SemIzquierda = new JPanel();
		SemIzquierda.setBackground(Color.DARK_GRAY);
		SemIzquierda.setBounds(264, 233, 34, 87);
		frame.getContentPane().add(SemIzquierda);
		SemIzquierda.setLayout(null);
		
		JPanel SemAbajo = new JPanel();
		SemAbajo.setBackground(Color.DARK_GRAY);
		SemAbajo.setBounds(416, 331, 34, 71);
		frame.getContentPane().add(SemAbajo);
		SemAbajo.setLayout(null);
		
//Labels de las luces de cada semaforo
		RojoAbajo.setBounds(0, 20, 46, 31);
		SemAbajo.add(RojoAbajo);
		
		RojoArriba.setBounds(0, 20, 48, 30);
		SemArriba.add(RojoArriba);
		
		RojoIzquierda.setBounds(0, 31, 46, 30);
		SemIzquierda.add(RojoIzquierda);
	
		RojoDerecho.setBounds(0, 28, 62, 34);
		SemDerecha.add(RojoDerecho);
		
		JPanel panel_8 = new JPanel();
		panel_8.setBackground(Color.ORANGE);
		panel_8.setBounds(10, 273, 44, 10);
		frame.getContentPane().add(panel_8);
		
		JPanel panel_9 = new JPanel();
		panel_9.setBackground(Color.ORANGE);
		panel_9.setBounds(64, 273, 44, 10);
		frame.getContentPane().add(panel_9);
		
		JPanel panel_10 = new JPanel();
		panel_10.setBackground(Color.ORANGE);
		panel_10.setBounds(118, 273, 44, 10);
		frame.getContentPane().add(panel_10);
		
		JPanel panel_11 = new JPanel();
		panel_11.setBackground(Color.ORANGE);
		panel_11.setBounds(172, 273, 44, 10);
		frame.getContentPane().add(panel_11);
		
		JPanel panel_12 = new JPanel();
		panel_12.setBackground(Color.ORANGE);
		panel_12.setBounds(823, 273, 44, 10);
		frame.getContentPane().add(panel_12);
		
		JPanel panel_13 = new JPanel();
		panel_13.setBackground(Color.ORANGE);
		panel_13.setBounds(769, 273, 44, 10);
		frame.getContentPane().add(panel_13);
		
		JPanel panel_14 = new JPanel();
		panel_14.setBackground(Color.ORANGE);
		panel_14.setBounds(715, 273, 44, 10);
		frame.getContentPane().add(panel_14);
		
		JPanel panel_15 = new JPanel();
		panel_15.setBackground(Color.ORANGE);
		panel_15.setBounds(661, 273, 44, 10);
		frame.getContentPane().add(panel_15);
		
		JPanel panel_16 = new JPanel();
		panel_16.setBackground(Color.ORANGE);
		panel_16.setBounds(428, 11, 10, 44);
		frame.getContentPane().add(panel_16);
		
		JPanel panel_17 = new JPanel();
		panel_17.setBackground(Color.ORANGE);
		panel_17.setBounds(428, 66, 10, 44);
		frame.getContentPane().add(panel_17);
		
		JPanel panel_18 = new JPanel();
		panel_18.setBackground(Color.ORANGE);
		panel_18.setBounds(428, 424, 10, 44);
		frame.getContentPane().add(panel_18);
		
		JPanel panel_19 = new JPanel();
		panel_19.setBackground(Color.ORANGE);
		panel_19.setBounds(428, 479, 10, 44);
		frame.getContentPane().add(panel_19);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(10, 271, 142, 63);
		frame.getContentPane().add(lblNewLabel);
		Image imagen = new ImageIcon(this.getClass().getResource("/Flecha_derecha_2.png")).getImage();
		lblNewLabel.setIcon(new ImageIcon(imagen));
		
		JLabel label = new JLabel("");
		label.setBounds(795, 282, 118, 52);
		frame.getContentPane().add(label);
		label.setIcon(new ImageIcon(imagen));
		
		JLabel label_1 = new JLabel("");
		label_1.setBounds(795, 218, 106, 44);
		frame.getContentPane().add(label_1);
		Image imagen_2 = new ImageIcon(this.getClass().getResource("/Flecha_izquierda.png")).getImage();
		label_1.setIcon(new ImageIcon(imagen_2));
		
		JLabel label_2 = new JLabel("");
		label_2.setBounds(10, 218, 106, 44);
		frame.getContentPane().add(label_2);
		label_2.setIcon(new ImageIcon(imagen_2));
		
		JLabel label_3 = new JLabel("");
		label_3.setBounds(472, 14, 77, 96);
		frame.getContentPane().add(label_3);
		Image imagen_3 = new ImageIcon(this.getClass().getResource("/Flecha_arriba.png")).getImage();
		label_3.setIcon(new ImageIcon(imagen_3));
		
		JLabel label_4 = new JLabel("");
		label_4.setBounds(472, 446, 77, 96);
		frame.getContentPane().add(label_4);
		label_4.setIcon(new ImageIcon(imagen_3));
		
		JLabel label_5 = new JLabel("");
		label_5.setBounds(318, 14, 77, 71);
		frame.getContentPane().add(label_5);
		Image imagen_4 = new ImageIcon(this.getClass().getResource("/Flecha_abajo.png")).getImage();
		label_5.setIcon(new ImageIcon(imagen_4));
		
		JLabel label_6 = new JLabel("");
		label_6.setBounds(318, 455, 77, 87);
		frame.getContentPane().add(label_6);
		label_6.setIcon(new ImageIcon(imagen_4));
		AutosCarrilSup.setFont(new Font("Yu Gothic Medium", Font.BOLD | Font.ITALIC, 25));
		
		
		AutosCarrilSup.setBounds(308, 151, 99, 71);
		frame.getContentPane().add(AutosCarrilSup);
		AutosCarrilIzq.setFont(new Font("Yu Gothic Medium", Font.BOLD | Font.ITALIC, 25));
		
		
		AutosCarrilIzq.setBounds(149, 286, 99, 34);
		frame.getContentPane().add(AutosCarrilIzq);
		AutosCarrilInf.setFont(new Font("Yu Gothic Medium", Font.BOLD | Font.ITALIC, 25));
		
		
		AutosCarrilInf.setBounds(460, 330, 99, 72);
		frame.getContentPane().add(AutosCarrilInf);
		AutosCarrilDer.setFont(new Font("Yu Gothic Medium", Font.BOLD | Font.ITALIC, 25));
		
		
		AutosCarrilDer.setBounds(623, 233, 99, 34);
		frame.getContentPane().add(AutosCarrilDer);
		
	}
	
	public static int Aleatorio() {
		int aleatorio=0;
		aleatorio= (int)(Math.random()*5);
		return aleatorio;
	}
}
