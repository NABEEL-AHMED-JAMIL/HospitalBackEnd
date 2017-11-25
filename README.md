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
	//@Cacheable(value="products", key="#name", condition="#name!='HTC'" , unless="#result==null")
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
```
Remark: Instead of using @EnableAutoConfiguration, @ComponentScan & @Configuration on a class, you could just use only @SpringBootApplication which is equivalent to using @Configuration, @EnableAutoConfiguration and @ComponentScan with their
default attributes.
```
```
-----------------------------------------------------
| How to work with "Local and Remote" and "Jar and Web" together  |
-----------------------------------------------------
1) packagin name ---> <packaging>${artifact-packaging}</packaging>
2) project name -----> <name>${project-artifactId}</name>
3) create the profile
<profiles>
	<profile>
		<id>local</id>
		<activation>	
		      <activeByDefault>true</activeByDefault>
		</activaation>
		<properties>
		      <artifact-packaging>jar</artifact-packaging>
		</properties>
        </profile>
	<profile>
		<id>remote</id>
		<properties>
			<artifact-packaging>war</artifact-packaging>
		</properties>
		<dependencies>
			<dependency>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-starter-tomcat</artifactId>
				<scope>provided</scope>
			</dependency>
		</dependencies>
		<build>
			<plugins>
				<plugin>
					<groupId>org.springframework.boot</groupId>
					<artifactId>spring-boot-maven-plugin</artifactId>
					<version>1.4.3.RELEASE</version>
					<configuration>
						<finalName>${project.artifactId}</finalName>
					</configuration>
				</plugin>
			</plugins>
		</build>
	</profile>	
</profiles>

```
### JSON And XML Rest Service used the Below dependencies
```
1) if you want to used the xml dependency you should add the dependency like
<dependency>
    <groupId>com.fasterxml.jackson.dataformat</groupId>
    <artifactId>jackson-dataformat-xml</artifactId>
</dependency>
Note:- data type must be like "‘Accept’ header with value ‘text/xml’ or ‘application/xml’."
```
### Sending back the value on Url
```
// -------------------Create a User-------------------------------------------
 
    @RequestMapping(value = "/user/", method = RequestMethod.POST)
    public ResponseEntity<?> createUser(@RequestBody User user, UriComponentsBuilder ucBuilder) {
        logger.info("Creating User : {}", user);
 
        if (userService.isUserExist(user)) {
            logger.error("Unable to create. A User with name {} already exist", user.getName());
            return new ResponseEntity(new CustomErrorType("Unable to create. A User with name " + 
            user.getName() + " already exist."),HttpStatus.CONFLICT);
        }
        userService.saveUser(user);
 
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/user/{id}").buildAndExpand(user.getId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }
```

### User the Credentials class
```
public class Credentials {
     private String username;
     private String password;
     
     public Credentials(String username, String password){
          this.username = username;
	  this.password = password;
     }
     // getter and setter.... toString
}
```
