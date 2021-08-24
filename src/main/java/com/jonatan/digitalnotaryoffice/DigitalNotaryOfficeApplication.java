package com.jonatan.digitalnotaryoffice;

import java.util.List;

import com.jonatan.digitalnotaryoffice.domain.entity.Address;
import com.jonatan.digitalnotaryoffice.domain.entity.Certificate;
import com.jonatan.digitalnotaryoffice.domain.entity.Notary;
import com.jonatan.digitalnotaryoffice.domain.services.AddressService;
import com.jonatan.digitalnotaryoffice.domain.services.CertificateService;
import com.jonatan.digitalnotaryoffice.domain.services.NotaryService;
import com.jonatan.digitalnotaryoffice.domain.util.Seed;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DigitalNotaryOfficeApplication {

	public static void main(String[] args) {
		SpringApplication.run(DigitalNotaryOfficeApplication.class, args);
	}

	@Bean
	public CommandLineRunner run(AddressService addressService, CertificateService certificateService, NotaryService notaryService) {
		
		return args -> {
			List<Address> addresses = Seed.createAddresses();
			addresses.forEach( address -> addressService.saveAddress(address) );

			List<Certificate> certificates = Seed.createCertificates();
			certificates.forEach( certificate -> certificateService.saveCertificate(certificate) );			

			List<Notary> notaries = Seed.createNotaries(addresses, certificates);
			notaries.forEach( notary -> notaryService.saveNotary(notary) );	
		};
	}

}
