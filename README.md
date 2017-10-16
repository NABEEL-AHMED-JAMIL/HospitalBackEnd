# HospitalBackEnd
Hospital BackEnd using Spring-boot,Spring-Security,Spring-mail,Buil-tomCat no need the outter TomCat
The project will soon add the "Docker image" cantainer

## Spring Cache
A Caching behaviour checking whether the method has been already executed for the given arguments. If it has, then the cached result is returned without having to execute the actual method.
if it has not, then method is executed, the result cached and returned to the user so that, the next time the method is invoked, the cached result is returned.

@Repository
public class MemoryMessageRepository implements MessageReposiotry {
	
	private static final Logger LOG = LoggerFactory.getLogger(MemoryMessageRepository.class);
	
	// ConcurrentHashMap when you need very high concurrency in your project.
	// It is thread safe without synchronizing the whole map.
	// Reads can happen very fast while write is done with a lock.
	private final Map<String, Message> messages = new ConcurrentHashMap<String, Message>();
	
	@Cacheable("messages")
	public Message getMessage(String title) {
		LOG.info("Fetching messages");
		return messages.get(title);
	}
	
	@CacheEvict(value = "message", key = "message.title")
	public void save(Message messages) {
		LOG.info("Saving message");
		message.put(message.getTitle(), message);
	}
        
        @Cacheable(value = "employee", key = "#surname")
        public Person findEmployeeBySurname(String firstName, String surname, int age) {
                return new Person(firstName, surname, age);
         }
	
	public Collection<Message> findAll() {
		return message.values();
	}
	
	@PostConstruct
	public void addSomeDefaultMessage() {
		save(new Message("Hello", "Hello World"));
		save(new Message("Appointment", "Remeber the milk!"));
	}
  
  private static final List<String> SAMPLE_COUNTRY_CODES = Arrays.asList("AF", "AX",
			"AL", "DZ", "AS", "AD", "AO", "AI", "AQ", "AG", "AR", "AM", "AW", "AU", "AT",
			"AZ", "BS", "BH", "BD", "BB", "BY", "BE", "BZ", "BJ", "BM", "BT", "BO", "BQ",
			"BA", "BW", "BV", "BR", "IO", "BN", "BG", "BF", "BI", "KH", "CM", "CA", "CV",
			"KY", "CF", "TD", "CL", "CN", "CX", "CC", "CO", "KM", "CG", "CD", "CK", "CR",
			"CI", "HR", "CU", "CW", "CY", "CZ", "DK", "DJ", "DM", "DO", "EC", "EG", "SV",
			"GQ", "ER", "EE", "ET", "FK", "FO", "FJ", "FI", "FR", "GF", "PF", "TF", "GA",
			"GM", "GE", "DE", "GH", "GI", "GR", "GL", "GD", "GP", "GU", "GT", "GG", "GN",
			"GW", "GY", "HT", "HM", "VA", "HN", "HK", "HU", "IS", "IN", "ID", "IR", "IQ",
			"IE", "IM", "IL", "IT", "JM", "JP", "JE", "JO", "KZ", "KE", "KI", "KP", "KR",
			"KW", "KG", "LA", "LV", "LB", "LS", "LR", "LY", "LI", "LT", "LU", "MO", "MK",
			"MG", "MW", "MY", "MV", "ML", "MT", "MH", "MQ", "MR", "MU", "YT", "MX", "FM",
			"MD", "MC", "MN", "ME", "MS", "MA", "MZ", "MM", "NA", "NR", "NP", "NL", "NC",
			"NZ", "NI", "NE", "NG", "NU", "NF", "MP", "NO", "OM", "PK", "PW", "PS", "PA",
			"PG", "PY", "PE", "PH", "PN", "PL", "PT", "PR", "QA", "RE", "RO", "RU", "RW",
			"BL", "SH", "KN", "LC", "MF", "PM", "VC", "WS", "SM", "ST", "SA", "SN", "RS",
			"SC", "SL", "SG", "SX", "SK", "SI", "SB", "SO", "ZA", "GS", "SS", "ES", "LK",
			"SD", "SR", "SJ", "SZ", "SE", "CH", "SY", "TW", "TJ", "TZ", "TH", "TL", "TG",
			"TK", "TO", "TT", "TN", "TR", "TM", "TC", "TV", "UG", "UA", "AE", "GB", "US",
			"UM", "UY", "UZ", "VU", "VE", "VN", "VG", "VI", "WF", "EH", "YE", "ZM", "ZW");

	
}
## Crose Origin
@CrossOrigin(origins = "http://localhost:4200")
