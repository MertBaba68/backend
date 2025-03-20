package nl.vodafoneZiggo.partnerForProgress;

import nl.vodafoneZiggo.partnerForProgress.services.data.CategoriesRepository;
import nl.vodafoneZiggo.partnerForProgress.services.data.ServiceRepository;
import nl.vodafoneZiggo.partnerForProgress.services.domain.Category;
import nl.vodafoneZiggo.partnerForProgress.services.domain.Information;
import nl.vodafoneZiggo.partnerForProgress.services.domain.Service;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Component
public class DummyDataRunner implements CommandLineRunner {

    private final CategoriesRepository categoriesRepository;
    private final ServiceRepository serviceRepository;

    public DummyDataRunner(CategoriesRepository categoriesRepository, ServiceRepository serviceRepository) {
        this.categoriesRepository = categoriesRepository;
        this.serviceRepository = serviceRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("CONsole");
        // Check if data already exists
//        if (categoriesRepository.count() == 0 && serviceRepository.count() == 0) {
            // Create categories and services
            List<Category> categories = Arrays.asList(
                    createCategory("Agriculture", Arrays.asList(
                            createService("Precision Farming Systems", "Gebruik GPS en sensoren om planten en oogsten te optimaliseren voor een hogere opbrengst."),
                            createService("Smart Irrigation Management", "Automatische irrigatiesystemen die watergebruik aanpassen aan bodemcondities."),
                            createService("Livestock Health Monitoring", "Draagbare apparaten die de gezondheid van vee volgen en waarschuwen bij afwijkingen."),
                            createService("Crop Yield Prediction", "Gebruik AI en data-analyse om oogsten te voorspellen en strategieën aan te passen."),
                            createService("Sustainable Pest Control", "Biologische methoden om plagen te bestrijden zonder milieuschade."),
                            createService("Vertical Farming Solutions", "Ruimtebesparende landbouwmethoden voor stedelijke gebieden.")
                    )),
                    createCategory("Healthcare", Arrays.asList(
                            createService("Telemedicine Platforms", "Online consultatieplatforms voor patiënten om remote medische hulp te ontvangen."),
                            createService("AI Diagnostics Assistant", "Artificiële intelligentie die medische beelden analyseert voor snellere diagnose."),
                            createService("Wearable Health Monitors", "Draagbare apparaten die vitale functies zoals hartslag en bloeddruk continu monitoren."),
                            createService("Personalized Medicine", "Behandelingen op maat gebaseerd op genetische gegevens van de patiënt."),
                            createService("Virtual Reality Therapy", "VR-toepassingen voor pijnbestrijding en behandeling van fobieën."),
                            createService("Electronic Health Records", "Digitale patiëntendossiers voor betere gegevensuitwisseling tussen zorgverleners.")
                    )),
                    createCategory("Technology", Arrays.asList(
                            createService("Cybersecurity Solutions", "Geavanceerde beveiligingssystemen tegen cyberdreigingen en datalekken."),
                            createService("Cloud Computing Services", "Schalbare cloudoplossingen voor dataopslag en verwerking."),
                            createService("Internet of Things Platforms", "IoT-platforms voor verbonden apparaten in industriële en consumentenomgevingen."),
                            createService("Artificial Intelligence Tools", "AI-gedreven tools voor automatisering en gegevensanalyse."),
                            createService("Blockchain Development", "Beveiligde blockchain-systemen voor transacties en data-integriteit."),
                            createService("DevOps Automation", "Automatisering van softwareontwikkeling en implementatieprocessen.")
                    )),
                    createCategory("Finance", Arrays.asList(
                            createService("Mobile Payment Solutions", "Mobiele betaalplatforms voor contactloze transacties."),
                            createService("Blockchain Banking", "Decentrale financiële systemen gebaseerd op blockchain-technologie."),
                            createService("Robo-Advisory Services", "Automatische beleggingsadviesdiensten gebaseerd op AI."),
                            createService("Cryptocurrency Trading", "Platformen voor het kopen en verkopen van cryptocurrencies."),
                            createService("Digital Wallets", "Elektronische portemonnees voor veilige opslag van betaalgegevens."),
                            createService("Financial Analytics", "Geavanceerde financiële analyse tools voor bedrijfsprestaties.")
                    )),
                    createCategory("Education", Arrays.asList(
                            createService("Online Learning Platforms", "Interactieve leerplatforms voor afstandsonderwijs en online cursussen."),
                            createService("AI Tutoring Systems", "Persoonlijke leerassistenten die studenten real-time helpen."),
                            createService("Virtual Reality Classrooms", "VR-toepassingen voor immersieve leerervaringen."),
                            createService("Educational Apps", "Mobiele applicaties voor kinderen om basisvaardigheden te leren."),
                            createService("Skill Development Courses", "Specialisatiecursussen voor professionele vaardigheden."),
                            createService("Adaptive Learning", "Leersystemen die zich aanpassen aan de capaciteiten van de leerling.")
                    )),
                    createCategory("Energy", Arrays.asList(
                            createService("Solar Panel Systems", "Zonnepanelen voor duurzame energieopwekking."),
                            createService("Wind Energy Solutions", "Windturbines voor grootschalige energieopwekking."),
                            createService("Smart Grids", "Intelligente energienetwerken voor efficiënt energiegebruik."),
                            createService("Energy Storage Systems", "Oplossingen voor opslag van hernieuwbare energie."),
                            createService("Green Hydrogen Production", "Productie van groene waterstof als schone brandstof."),
                            createService("Energy Efficiency Consulting", "Adviesdiensten voor vermindering van energieverbruik.")
                    ))
            );

            // Save categories and services to the database
            categoriesRepository.saveAll(categories);
//        }
    }

    private Category createCategory(String name, List<Service> services) {
        return new Category(name, getRandomImage(), services);
    }

    private Service createService(String name, String description) {
        // Maak een lijst van Information-objecten voor de service
        List<Information> about = Arrays.asList(
                new Information("Payoff", "Maak briljante werkplekbeslissingen.", Arrays.asList(
                        "Voor medewerkers: virtuele assistentie voor soepele werkdagen.",
                        "Voor kantoormanagers: ruimte-analyse om je werkplek te optimaliseren.",
                        "Voor leidinggevenden: nieuwe zakelijke voordelen met een slim kantoor."
                ), getRandomImage()),
                new Information("Waardepropositie", "Nimway lost problemen op met betrekking tot inefficiënt gebruik van kantoorruimtes.", null, getRandomImage()),
                new Information("Oplossing", "Nimway is een complete suite van slimme kantoorsensoren, schermen en applicaties.", Arrays.asList(
                        "Real-time sensoren voor live data.",
                        "Workplace experience tools voor betere besluitvorming.",
                        "Eenvoudige integratie met bestaande systemen."
                ), getRandomImage()),
                new Information("Uitkomst", "Door Nimway te implementeren, kunnen bedrijven hun kantoorruimtes optimaliseren.", null, getRandomImage())
        );

        return new Service(name, description, getRandomImage(), about);
    }

    private String getRandomImage() {
        return Math.random() < 0.5 ? "/utility industry (1).png" : "/tracking industry (1).png";
    }
}