package com.jonatan.digitalnotaryoffice.domain.util;

import java.util.Arrays;
import java.util.List;

import com.jonatan.digitalnotaryoffice.domain.entity.Address;
import com.jonatan.digitalnotaryoffice.domain.entity.Certificate;
import com.jonatan.digitalnotaryoffice.domain.entity.Notary;

public abstract class Seed {

    /**
	 * Create simple adresses for demostration
	 * 
	 * @return
	 */
	public static List<Address> createAdresses() {
		Address address1 = new Address();
		address1.setCep(95900000);
		address1.setComplement("Sala 01");
		address1.setDistrict("Centro");
		address1.setCity("Lajeado");
		address1.setState("Rio Grande do Sul");
		address1.setUf("RS");
		address1.setStreet("R. Santos Filho, 394");

		Address address2 = new Address();
		address2.setCep(1220010);
		address2.setDistrict("República");
		address2.setCity("São Paulo");
		address2.setState("São Paulo");
		address2.setUf("SP");
		address2.setStreet("R. Rêgo Freitas, 133");

		Address address3 = new Address();
		address3.setCep(90040191);
		address3.setDistrict("Cidade Baixa");
		address3.setCity("Porto Alegre");
		address3.setState("Rio Grande do Sul");
		address3.setUf("RS");
		address3.setStreet("Av. Venâncio Aires, 243");

		return Arrays.asList(new Address[]{address1,address2,address3});
	}

	/**
	 * Create simple certificates for demostration
	 * 
	 * @return
	 */
	public static List<Certificate> createCertificates() {
		Certificate certificate1 = new Certificate();
		certificate1.setName("Certidão de nascimento");
		certificate1.setDescription("Descrição certidão de nascimento");

		Certificate certificate2 = new Certificate();
		certificate2.setName("Certidão de casamento");
		certificate2.setVersion("1.1");
		certificate2.setDescription("Descrição certidão de casamento");

		Certificate certificate3 = new Certificate();
		certificate3.setName("Certidão de óbito");
		certificate3.setDescription("Descrição certidão de óbito");

		return Arrays.asList(new Certificate[]{certificate1, certificate2, certificate3});
	}

	/**
	 * Create simple notaries for demostration
     * Just for data, never implement like this
	 * 
     * @param adresses List<Address>
     * @param certificates List<Certificate>
	 * @return
	 */
	public static List<Notary> createNotaries(List<Address> adresses, List<Certificate> certificates) {
		Notary notary1 = new Notary();
		notary1.setName("Cartório Eleitoral de Lajeado");
		notary1.setAddress(adresses.get(0));
		notary1.setCertificates(certificates.subList(0, 2));

		Notary notary2 = new Notary();
		notary2.setName("Cartório do 2º Tabelião de Notas de São Paulo - SP (Segundo Notas)");
		notary2.setAddress(adresses.get(1));
		notary2.setCertificates(certificates);

		Notary notary3 = new Notary();
		notary3.setName("2º Cartório de Registro Civil");
		notary3.setAddress(adresses.get(2));
		notary3.setCertificates(certificates.subList(1, 2));		

		return Arrays.asList(new Notary[]{notary1, notary2, notary3});
	}
}
