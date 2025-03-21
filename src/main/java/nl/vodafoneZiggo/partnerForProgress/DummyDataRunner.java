package nl.vodafoneZiggo.partnerForProgress;

import nl.vodafoneZiggo.partnerForProgress.services.data.CategoriesRepository;
import nl.vodafoneZiggo.partnerForProgress.services.data.ServiceRepository;
import nl.vodafoneZiggo.partnerForProgress.services.domain.Category;
import nl.vodafoneZiggo.partnerForProgress.services.domain.Information;
import nl.vodafoneZiggo.partnerForProgress.services.domain.Service;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
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
        System.out.println("Loading dummy data...");

        // Check if data already exists
        if (categoriesRepository.count() == 0 && serviceRepository.count() == 0) {
            // Create categories and services
            List<Category> categories = Arrays.asList(
                    createCategory("Agriculture", Arrays.asList(
                            createService("Precision Farming Systems", "Gebruik GPS en sensoren om planten en oogsten te optimaliseren voor een hogere opbrengst.", "Agriculture"),
                            createService("Smart Irrigation Management", "Automatische irrigatiesystemen die watergebruik aanpassen aan bodemcondities.", "Agriculture"),
                            createService("Livestock Health Monitoring", "Draagbare apparaten die de gezondheid van vee volgen en waarschuwen bij afwijkingen.", "Agriculture"),
                            createService("Crop Yield Prediction", "Gebruik AI en data-analyse om oogsten te voorspellen en strategieën aan te passen.", "Agriculture"),
                            createService("Sustainable Pest Control", "Biologische methoden om plagen te bestrijden zonder milieuschade.", "Agriculture"),
                            createService("Vertical Farming Solutions", "Ruimtebesparende landbouwmethoden voor stedelijke gebieden.", "Agriculture")
                    )),
                    createCategory("Healthcare", Arrays.asList(
                            createService("Telemedicine Platforms", "Online consultatieplatforms voor patiënten om remote medische hulp te ontvangen.", "Healthcare"),
                            createService("AI Diagnostics Assistant", "Artificiële intelligentie die medische beelden analyseert voor snellere diagnose.", "Healthcare"),
                            createService("Wearable Health Monitors", "Draagbare apparaten die vitale functies zoals hartslag en bloeddruk continu monitoren.", "Healthcare"),
                            createService("Personalized Medicine", "Behandelingen op maat gebaseerd op genetische gegevens van de patiënt.", "Healthcare"),
                            createService("Virtual Reality Therapy", "VR-toepassingen voor pijnbestrijding en behandeling van fobieën.", "Healthcare"),
                            createService("Electronic Health Records", "Digitale patiëntendossiers voor betere gegevensuitwisseling tussen zorgverleners.", "Healthcare")
                    )),
                    createCategory("Technology", Arrays.asList(
                            createService("Cybersecurity Solutions", "Geavanceerde beveiligingssystemen tegen cyberdreigingen en datalekken.", "Technology"),
                            createService("Cloud Computing Services", "Schalbare cloudoplossingen voor dataopslag en verwerking.", "Technology"),
                            createService("Internet of Things Platforms", "IoT-platforms voor verbonden apparaten in industriële en consumentenomgevingen.", "Technology"),
                            createService("Artificial Intelligence Tools", "AI-gedreven tools voor automatisering en gegevensanalyse.", "Technology"),
                            createService("Blockchain Development", "Beveiligde blockchain-systemen voor transacties en data-integriteit.", "Technology"),
                            createService("DevOps Automation", "Automatisering van softwareontwikkeling en implementatieprocessen.", "Technology")
                    )),
                    createCategory("Finance", Arrays.asList(
                            createService("Mobile Payment Solutions", "Mobiele betaalplatforms voor contactloze transacties.", "Finance"),
                            createService("Blockchain Banking", "Decentrale financiële systemen gebaseerd op blockchain-technologie.", "Finance"),
                            createService("Robo-Advisory Services", "Automatische beleggingsadviesdiensten gebaseerd op AI.", "Finance"),
                            createService("Cryptocurrency Trading", "Platformen voor het kopen en verkopen van cryptocurrencies.", "Finance"),
                            createService("Digital Wallets", "Elektronische portemonnees voor veilige opslag van betaalgegevens.", "Finance"),
                            createService("Financial Analytics", "Geavanceerde financiële analyse tools voor bedrijfsprestaties.", "Finance")
                    )),
                    createCategory("Education", Arrays.asList(
                            createService("Online Learning Platforms", "Interactieve leerplatforms voor afstandsonderwijs en online cursussen.", "Education"),
                            createService("AI Tutoring Systems", "Persoonlijke leerassistenten die studenten real-time helpen.", "Education"),
                            createService("Virtual Reality Classrooms", "VR-toepassingen voor immersieve leerervaringen.", "Education"),
                            createService("Educational Apps", "Mobiele applicaties voor kinderen om basisvaardigheden te leren.", "Education"),
                            createService("Skill Development Courses", "Specialisatiecursussen voor professionele vaardigheden.", "Education"),
                            createService("Adaptive Learning", "Leersystemen die zich aanpassen aan de capaciteiten van de leerling.", "Education")
                    )),
                    createCategory("Energy", Arrays.asList(
                            createService("Solar Panel Systems", "Zonnepanelen voor duurzame energieopwekking.", "Energy"),
                            createService("Wind Energy Solutions", "Windturbines voor grootschalige energieopwekking.", "Energy"),
                            createService("Smart Grids", "Intelligente energienetwerken voor efficiënt energiegebruik.", "Energy"),
                            createService("Energy Storage Systems", "Oplossingen voor opslag van hernieuwbare energie.", "Energy"),
                            createService("Green Hydrogen Production", "Productie van groene waterstof als schone brandstof.", "Energy"),
                            createService("Energy Efficiency Consulting", "Adviesdiensten voor vermindering van energieverbruik.", "Energy")
                    ))
            );

            // Save categories and services to the database
            categoriesRepository.saveAll(categories);
        }
    }

    private Category createCategory(String name, List<Service> services) {
        return new Category(name, getRandomHeaderImage(), services);
    }

    private Service createService(String name, String description, String category) {
        // Makes an list of Information object for Service, no matter the category
        List<Information> about = new ArrayList<>();

        // Algemene informatie die bij alle services past
        about.add(new Information("Payoff", "Maak briljante werkplekbeslissingen.", Arrays.asList(
                "Haal nog meer uit je kantoorruimtes. Nimway van Sony is een complete suite van slimme kantoorsensoren, schermen en applicaties.",
                "Kiezen voor Nimway betekent een benadering omarmen die werkplekken aanpast aan een nieuwe realiteit.",
                "Voor medewerkers: virtuele assistentie voor soepele werkdagen.",
                "Voor kantoormanagers: ruimte-analyse om je werkplek te optimaliseren.",
                "Voor leidinggevenden: nieuwe zakelijke voordelen met een slim kantoor."
        ), getRandomInformationImage()));

        // Category-specific info
        switch (category) {
            case "Agriculture", "Healthcare":
                about.add(new Information("Waardepropositie", "Nimway lost problemen op met betrekking tot inefficiënt gebruik van kantoorruimtes.", Arrays.asList(
                        "Het biedt bedrijven de mogelijkheid om hun werkplekken effectiever te gebruiken en werknemers te ondersteunen in hun dagelijkse werkzaamheden."
                ), getRandomInformationImage()));
                about.add(new Information("Oplossing", "Nimway is een complete suite van slimme kantoorsensoren, schermen en applicaties.", Arrays.asList(
                        "Het systeem maakt gebruik van real-time sensoren en workplace experience tools om directe kantoorintelligentie te bieden.",
                        "Dit stelt zowel bedrijven als werknemers in staat om weloverwogen beslissingen te nemen en de werkplekervaring te optimaliseren."
                ), getRandomInformationImage()));
                about.add(new Information("Uitkomst", "Door Nimway te implementeren, kunnen bedrijven hun kantoorruimtes optimaliseren.", Arrays.asList(
                        "Dit leidt tot een efficiënter gebruik van middelen en een verbeterde werkplekervaring voor werknemers.",
                        "Dit kan resulteren in kostenbesparingen en een verhoogde productiviteit."
                ), getRandomInformationImage()));
                break;
            // For in the future, add more cases
        }

        // Optional extra info
        if (Math.random() < 0.5) { // 50% chance for more info
            about.add(new Information("Extra Informatie", "Meer details over deze service.", null, getRandomInformationImage()));
        }

        return new Service(name, description, getRandomHeaderImage(), about);
    }

    private String getRandomHeaderImage() {
        return convertImageToBase64(Math.random() < 0.5 ? "images/utility industry.png" : "images/tracking industry.png");
    }

    private String getRandomInformationImage() {
        double chance = Math.random();
        if (chance < 0.3) { // 30% chance of no image
            return null;
        } else if (chance < 0.65) { // 35% chance for first img
            return convertImageToBase64("images/utility industry.png");
        } else { // 35% chance for second img
            return convertImageToBase64("images/tracking industry.png");
        }
    }

    private String convertImageToBase64(String imagePath) {
        try {
            byte[] imageBytes = Files.readAllBytes(Paths.get(imagePath));
            String base64Image = Base64.getEncoder().encodeToString(imageBytes);
            return base64Image;
        } catch (IOException e) {
            System.err.println("Error reading img: " + imagePath);
            e.printStackTrace();
            return null;
        }
    }
}
