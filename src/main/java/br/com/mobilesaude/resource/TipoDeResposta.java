package br.com.mobilesaude.resource;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.text.DecimalFormat;

@XmlRootElement(name = "TipoDeResposta")
@XmlAccessorType(XmlAccessType.FIELD)
@ManagedBean
@ViewScoped
public class TipoDeResposta {
	@XmlElement(name = "quantidade")
	private int qtd;
	@XmlElement(name = "resposta")
	private int response;
	@XmlElement(name = "porcentagemDoTipo")
	private String porcentagem;

	public TipoDeResposta() {

	}

	public TipoDeResposta(int qtd, int response, int qtdRequisicoes) {
		this.qtd = qtd;
		this.response = response;
		double d = ((double) (qtd) * 100.0 / (double) (qtdRequisicoes));
		this.porcentagem = converterDoubleParaDuasCasas(d);
	}

	private String converterDoubleParaDuasCasas(double d) {
		DecimalFormat df = new DecimalFormat("0.##");
		String dx = df.format(d);
		return dx;
	}

	public int getQtd() {
		return qtd;
	}

	public void setQtd(int qtd) {
		this.qtd = qtd;
	}

	public int getResponse() {
		return response;
	}

	public void setResponse(int response) {
		this.response = response;
	}

	public String getPorcentagem() {
		return porcentagem;
	}

}
