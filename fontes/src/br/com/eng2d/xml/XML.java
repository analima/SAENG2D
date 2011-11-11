package br.com.eng2d.xml;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import br.com.eng2d.objeto.Apoio1;
import br.com.eng2d.objeto.Apoio2;
import br.com.eng2d.objeto.Apoio3;
import br.com.eng2d.objeto.Forca;
import br.com.eng2d.objeto.Linha;
import br.com.eng2d.objeto.Objeto;
import br.com.eng2d.objeto.Ponto;

public class XML extends DefaultHandler {
	private static final String ELEMENTO_LINHA = "linha";
	private static final String ELEMENTO_PONTO = "ponto";
	private static final String ELEMENTO_FORCA = "forca";
	private static final String ELEMENTO_APOIO1 = "apoio1";
	private static final String ELEMENTO_APOIO2 = "apoio2";
	private static final String ELEMENTO_APOIO3 = "apoio3";
	private static final String VALOR_NEWTON = "valorNewton";
	private static final String ATRIBUTO_X = "x";
	private static final String ATRIBUTO_Y = "y";
	private static final String ATRIBUTO_X2 = "x2";
	private static final String ATRIBUTO_Y2 = "y2";
	private static final String ATRIBUTO_CX = "cX";
	private static final String ATRIBUTO_CY = "cY";
	private static final String ATRIBUTO_RAIO = "raio";
	private static final String ATRIBUTO_GRAU = "grau";
	private static final String ATRIBUTO_V1X = "v1X";
	private static final String ATRIBUTO_V1Y = "v1Y";
	private static final String ATRIBUTO_V2X = "v2X";
	private static final String ATRIBUTO_V2Y = "v2Y";
	private static final String ATRIBUTO_V3X = "v3X";
	private static final String ATRIBUTO_V3Y = "v3Y";
	private static final String ATRIBUTO_VEX = "vEX";
	private static final String ATRIBUTO_VEY = "vEY";
	private static final String ATRIBUTO_VDX = "vDX";
	private static final String ATRIBUTO_VDY = "vDY";
	private List<Objeto> objetos;
	
	public XML() {
		objetos = new ArrayList<Objeto>();
	}
	
	public void startElement(String uri, String localName, String name, Attributes attributes) throws SAXException {
		if(ELEMENTO_LINHA.equals(name)) {
			int x = 0;
			int y = 0;
			int x2 = 0;
			int y2 = 0;
			for(int i=0; i<attributes.getLength(); i++) {
				if(ATRIBUTO_X.equals(attributes.getQName(i))) {
					x = Integer.parseInt(attributes.getValue(i));
				} else if(ATRIBUTO_Y.equals(attributes.getQName(i))) {
					y = Integer.parseInt(attributes.getValue(i));
				} else if(ATRIBUTO_X2.equals(attributes.getQName(i))) {
					x2 = Integer.parseInt(attributes.getValue(i));
				} else if(ATRIBUTO_Y2.equals(attributes.getQName(i))) {
					y2 = Integer.parseInt(attributes.getValue(i));
				}
			}
			objetos.add(new Linha(x, y, x2, y2));
		} else if(ELEMENTO_PONTO.equals(name)) {
			int x = 0;
			int y = 0;
			for(int i=0; i<attributes.getLength(); i++) {
				if(ATRIBUTO_X.equals(attributes.getQName(i))) {
					x = Integer.parseInt(attributes.getValue(i));
				} else if(ATRIBUTO_Y.equals(attributes.getQName(i))) {
					y = Integer.parseInt(attributes.getValue(i));
				}
			}
			objetos.add(new Ponto(x, y));
		} else if(ELEMENTO_FORCA.equals(name)) {
			int cX = 0;
			int cY = 0;
			String valorNewton = "";
			int raio = 0;
			int grau = 0;
			float v1X = 0;
			float v1Y = 0;
			float v2X = 0;
			float v2Y = 0;
			for(int i=0; i<attributes.getLength(); i++) {
				if(ATRIBUTO_CX.equals(attributes.getQName(i))) {
					cX = Integer.parseInt(attributes.getValue(i));
				} else if(ATRIBUTO_CY.equals(attributes.getQName(i))) {
					cY = Integer.parseInt(attributes.getValue(i));
				} else if(VALOR_NEWTON.equals(attributes.getQName(i))) {
					valorNewton = attributes.getValue(i);
				} else if(ATRIBUTO_RAIO.equals(attributes.getQName(i))) {
					raio = Integer.parseInt(attributes.getValue(i));
				} else if(ATRIBUTO_GRAU.equals(attributes.getQName(i))) {
					grau = Integer.parseInt(attributes.getValue(i));
				} else if(ATRIBUTO_V1X.equals(attributes.getQName(i))) {
					v1X = Float.parseFloat(attributes.getValue(i));
				} else if(ATRIBUTO_V1Y.equals(attributes.getQName(i))) {
					v1Y = Float.parseFloat(attributes.getValue(i));
				} else if(ATRIBUTO_V2X.equals(attributes.getQName(i))) {
					v2X = Float.parseFloat(attributes.getValue(i));
				} else if(ATRIBUTO_V2Y.equals(attributes.getQName(i))) {
					v2Y = Float.parseFloat(attributes.getValue(i));
				}
			}
			Forca forca = new Forca(cX, cY, raio);
			forca.configEspecifico(valorNewton, grau, v1X, v1Y, v2X, v2Y);
			objetos.add(forca);
		} else if(ELEMENTO_APOIO1.equals(name)) {
			int cX = 0;
			int cY = 0;
			float v1X = 0;
			float v1Y = 0;
			float v2X = 0;
			float v2Y = 0;
			float v3X = 0;
			float v3Y = 0;
			for(int i=0; i<attributes.getLength(); i++) {
				if(ATRIBUTO_CX.equals(attributes.getQName(i))) {
					cX = Integer.parseInt(attributes.getValue(i));
				} else if(ATRIBUTO_CY.equals(attributes.getQName(i))) {
					cY = Integer.parseInt(attributes.getValue(i));
				} else if(ATRIBUTO_V1X.equals(attributes.getQName(i))) {
					v1X = Float.parseFloat(attributes.getValue(i));
				} else if(ATRIBUTO_V1Y.equals(attributes.getQName(i))) {
					v1Y = Float.parseFloat(attributes.getValue(i));
				} else if(ATRIBUTO_V2X.equals(attributes.getQName(i))) {
					v2X = Float.parseFloat(attributes.getValue(i));
				} else if(ATRIBUTO_V2Y.equals(attributes.getQName(i))) {
					v2Y = Float.parseFloat(attributes.getValue(i));
				} else if(ATRIBUTO_V3X.equals(attributes.getQName(i))) {
					v3X = Float.parseFloat(attributes.getValue(i));
				} else if(ATRIBUTO_V3Y.equals(attributes.getQName(i))) {
					v3Y = Float.parseFloat(attributes.getValue(i));
				}
			}
			Apoio1 apoio1 = new Apoio1(cX, cY, 0);
			apoio1.configEspecifico(v1X, v1Y, v2X, v2Y, v3X, v3Y);
			objetos.add(apoio1);
		} else if(ELEMENTO_APOIO2.equals(name)) {
			int cX = 0;
			int cY = 0;
			float v1X = 0;
			float v1Y = 0;
			float v2X = 0;
			float v2Y = 0;
			float v3X = 0;
			float v3Y = 0;
			float vEX = 0;
			float vEY = 0;
			float vDX = 0;
			float vDY = 0;
			for(int i=0; i<attributes.getLength(); i++) {
				if(ATRIBUTO_CX.equals(attributes.getQName(i))) {
					cX = Integer.parseInt(attributes.getValue(i));
				} else if(ATRIBUTO_CY.equals(attributes.getQName(i))) {
					cY = Integer.parseInt(attributes.getValue(i));
				} else if(ATRIBUTO_V1X.equals(attributes.getQName(i))) {
					v1X = Float.parseFloat(attributes.getValue(i));
				} else if(ATRIBUTO_V1Y.equals(attributes.getQName(i))) {
					v1Y = Float.parseFloat(attributes.getValue(i));
				} else if(ATRIBUTO_V2X.equals(attributes.getQName(i))) {
					v2X = Float.parseFloat(attributes.getValue(i));
				} else if(ATRIBUTO_V2Y.equals(attributes.getQName(i))) {
					v2Y = Float.parseFloat(attributes.getValue(i));
				} else if(ATRIBUTO_V3X.equals(attributes.getQName(i))) {
					v3X = Float.parseFloat(attributes.getValue(i));
				} else if(ATRIBUTO_V3Y.equals(attributes.getQName(i))) {
					v3Y = Float.parseFloat(attributes.getValue(i));
				} else if(ATRIBUTO_VEX.equals(attributes.getQName(i))) {
					vEX = Float.parseFloat(attributes.getValue(i));
				} else if(ATRIBUTO_VEY.equals(attributes.getQName(i))) {
					vEY = Float.parseFloat(attributes.getValue(i));
				} else if(ATRIBUTO_VDX.equals(attributes.getQName(i))) {
					vDX = Float.parseFloat(attributes.getValue(i));
				} else if(ATRIBUTO_VDY.equals(attributes.getQName(i))) {
					vDY = Float.parseFloat(attributes.getValue(i));
				}
			}
			Apoio2 apoio2 = new Apoio2(cX, cY, 0);
			apoio2.configEspecifico(v1X, v1Y, v2X, v2Y, v3X, v3Y, vEX, vEY, vDX, vDY);
			objetos.add(apoio2);
		} else if(ELEMENTO_APOIO3.equals(name)) {
			int cX = 0;
			int cY = 0;
			float v1X = 0;
			float v1Y = 0;
			float v2X = 0;
			float v2Y = 0;
			float v3X = 0;
			float v3Y = 0;
			for(int i=0; i<attributes.getLength(); i++) {
				if(ATRIBUTO_CX.equals(attributes.getQName(i))) {
					cX = Integer.parseInt(attributes.getValue(i));
				} else if(ATRIBUTO_CY.equals(attributes.getQName(i))) {
					cY = Integer.parseInt(attributes.getValue(i));
				} else if(ATRIBUTO_V1X.equals(attributes.getQName(i))) {
					v1X = Float.parseFloat(attributes.getValue(i));
				} else if(ATRIBUTO_V1Y.equals(attributes.getQName(i))) {
					v1Y = Float.parseFloat(attributes.getValue(i));
				} else if(ATRIBUTO_V2X.equals(attributes.getQName(i))) {
					v2X = Float.parseFloat(attributes.getValue(i));
				} else if(ATRIBUTO_V2Y.equals(attributes.getQName(i))) {
					v2Y = Float.parseFloat(attributes.getValue(i));
				} else if(ATRIBUTO_V3X.equals(attributes.getQName(i))) {
					v3X = Float.parseFloat(attributes.getValue(i));
				} else if(ATRIBUTO_V3Y.equals(attributes.getQName(i))) {
					v3Y = Float.parseFloat(attributes.getValue(i));
				}
			}
			Apoio3 apoio3 = new Apoio3(cX, cY, 0);
			apoio3.configEspecifico(v1X, v1Y, v2X, v2Y, v3X, v3Y);
			objetos.add(apoio3);
		}
	}
	
	public List<Objeto> getObjetos() {
		return objetos;
	}
}