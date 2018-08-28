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
		<executions>
      <execution>
          <id>copy-resources</id>
          <phase>validate</phase>
          <goals><goal>copy-resources</goal></goals>
          <configuration>
              <outputDirectory>${basedir}/target/classes/static/</outputDirectory >
              <resources>
                  <resource>
                      <directory>${basedir}/../angular4client/dist</directory >
                  </resource>
              </resources>
          </configuration>
      </execution>
</executions>
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
```
spring.datasource.url = jdbc:mysql://localhost:3306/test
spring.datasource.username =
spring.datasource.password =

# Keep the connection alive if idle for a long time (needed in production)
spring.datasource.testWhileIdle = true
spring.datasource.validationQuery = SELECT 1

# Show or not log for each sql query
spring.jpa.show-sql = true

# Hibernate ddl auto (create, create-drop, update)
spring.jpa.hibernate.ddl-auto = create-drop

# Naming strategy
spring.jpa.hibernate.naming-strategy = org.hibernate.cfg.ImprovedNamingStrategy

# Use spring.jpa.properties.* for Hibernate native properties (the prefix is
# stripped before adding them to the entity manager)

# The SQL dialect makes Hibernate generate better SQL for the chosen database
spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.MySQL5Dialect
```

### Linux Imp

https://theyellownumber.svn.cvsdude.com/admaxim/AdMaximRep/trunk/
https://theyellownumber.svn.cvsdude.com/admaximrepo/AdServerRepo/trunk
https://theyellownumber.svn.cvsdude.com/admaxim/adserverbranches/AdServerR008.7

1:  # ad
2:  # tl
3:  # ad | grep -i error
4:  # ad | grep -i exception
5:  # ad | grep -i monitor

https://www.computerhope.com/unix/ucut.htm
https://www.loggly.com/ultimate-guide/analyzing-linux-logs/

grep -o 'XsltProcessor has empty XSL file location map' adserver.log | wc -l
grep -20 'NullPointerException' adserver.log
ad | grep -i '{OpenRTB Bid Request Mapper}'
java.lang.NullPointerException
grep "MongoException" adserver.log | sort | uniq -c
grep "MongoException" adserver.log | sort | uniq -c | sort -r
grep "MongoException" adserver.log | cut -d ':' -f 7 | sort | uniq -c | sort -r
cut -f 2-4 data.txt
grep "UserDemographicManager" adserver.log | sort | uniq -c
cl
sudo -i
cd /opt/tomcat/bin
./shutdown.sh
pkill -9 java
rm -rf /server/*
./startup.sh



ssh -I D:\nabeel_pem\nabeel.pem nabeel@172.30.11.92

RTB-fr-2-Node-1::(ssh -I D:\nabeel_pem\nabeel.pem -p 6616 nabeel@172.30.10.244)


ad|grep -i 'com.admaxim.adserver.adprovider.util.AdProviderHelper'

0) ad ---> checked Ad Server Log ---> No Erro
heroku logs --tail|grep 'Request No#'

[25/May/2018:05:02:38,240 +0000] ERROR [partnerUIDLookup-77] com.admaxim.userprofile.dao.mongodb.PartnerUIDMapDAO  - MongoException: Value not present in this db 5 :in 404 ms: com.mongodb.MongoInterruptedException: Interrupted acquiring a permit to retrieve an item from the pool
 
	------------------------------------------
			No Error -- POOL
	------------------------------------------
	[29/Mar/2018:06:12:21,936 +0000] INFO  [http-80-70] com.admaxim.adserver.model.AdManager  - --PERF-- LocationRead : 0ms.
	[29/Mar/2018:06:12:21,938 +0000] INFO  [http-80-18] com.admaxim.adserver.model.AdManager  - --PERF-- LocationRead : 0ms.
	[29/Mar/2018:06:12:21,964 +0000] INFO  [http-80-18] com.admaxim.adserver.model.AdManager  - --PERF-- LocationRead : 0ms.

	--------------------------------------------------
	[29/Mar/2018:06:12:21,635 +0000] WARN  [http-80-35] com.admaxim.budget.CappingManager  - Removed ad: cID-74357 adGroupId--1 aID-87451 due to demographic Impression distribution Cap for GN_1 Daily cap 7000 Total cap 1000000 total count for GN : 211989.0 distribution for GN_1 : 102860.0 config percent for GN_1 : 25 remaining factor 0.788011 learning percent 20 variance 10

	--------------------------------------------------
	--------------------------------------------------
	!). ad|grep ERROR
	[29/Mar/2018:06:13:17,805 +0000] ERROR [http-80-35] com.admaxim.adserver.model.AdManager  - Site Disabled to serve: 6665_539463604
	[29/Mar/2018:06:13:19,055 +0000] ERROR [http-80-7] com.admaxim.adserver.model.AdManager  - PublisherSite null from cache :Invalid siteID: 6665_537260058
	[29/Mar/2018:06:13:19,357 +0000] ERROR [http-80-69] com.admaxim.adserver.model.AdManager  - Site Disabled to serve: 6665_539895324

	!!). ad|grep ERROR|grep -vE 't find ad for  Site Id|NOT CLASSIFIED NEW SITE|Site Disabled to serve'
	[29/Mar/2018:06:15:47,933 +0000] ERROR [partnerUIDLookup-91] com.admaxim.userprofile.dao.mongodb.PartnerUIDMapDAO  - MongoException: Value not present in this db 5 :in 11 ms: com.mongodb.MongoInterruptedException: Interrupted acquiring a permit to retrieve an item from the pool
	[29/Mar/2018:06:16:00,935 +0000] ERROR [partnerUIDLookup-76] com.admaxim.userprofile.dao.mongodb.PartnerUIDMapDAO  - MongoException: Value not present in this db 3 :in 18 ms: com.mongodb.MongoInterruptedException: Interrupted acquiring a permit to retrieve an item from the pool
	[29/Mar/2018:06:16:00,934 +0000] ERROR [partnerUIDLookup-73] com.admaxim.userprofile.dao.mongodb.PartnerUIDMapDAO  - MongoException: Value not present in this db 1 :in 18 ms: com.mongodb.MongoInterruptedException: Interrupted acquiring a permit to retrieve an item from the pool
	[29/Mar/2018:06:16:02,891 +0000] ERROR [partnerUIDLookup-94] com.admaxim.userprofile.dao.mongodb.PartnerUIDMapDAO  - MongoException: Value not present in this db 4 :in 17 ms: com.mongodb.MongoInterruptedException: Interrupted acquiring a permit to retrieve an item from the pool

	
   !!!). ad|grep -i exception
    !v). tl|grep -vE 'available|HTTP/1.1 204|HTTP/1.1 200'
   
	   Nothing showing up on monitor 
	  
   v). tl|grep -vE 'available'|awk '{if($11>100) print $0}' || tl|grep -vE 'available|HTTP/1.1 204'|awk '{if($11>100) print $0}'
   
   v!). ad|grep 'MongoDBMonitor'
   !) tail InitializationLog.txt
	[28/Mar/2018:06:38:25] - End of initialization : SiteListCacheTime Taken: 1 ms
	[28/Mar/2018:06:38:33] - Start initialization : SiteProfileRecordingTask for publisherId: 6665 number of siteProfile size is: 1

   !!) tail localhost_access_log.2018-03-29.txt   
	172.31.25.73 - - [28/Mar/2018:07:03:58 +0000] POST /adserver/openxBidder HTTP/1.1 204 - 146 application/json
	172.31.21.2 - - [28/Mar/2018:07:03:58 +0000] POST /adserver/openxBidder HTTP/1.1 204 - 171 application/json
	
--------------------------
RTB 1=> ironsourceBidder
RTB-2 => teadsBidder, unityBidder
RTB-3 => adexBidder
RTB-4 => axonixBidder
RTB-5 => mobfoxBidder 
RTB-7 => inneractiveBidder
---------------------------
RTB-FR-1 => adexBidder
RTB-FR-2 => openxBidder

# Linux Cmd
1) man ==> Manual pages
2) conf ==> configfile
3) pwd ==> show the current directory
4) cd ==> change your current directory
   1) cd /user  ==> move to next dir
   2) cd ==> back to home dir
   3) cd .. ==> parent directory
5) ls ==> list the contents of a directory with "ls"
6) ls -a ==> show all file also including hidden file.
7) tail ==> tail command to display the last few lines of a text file.
8) cat ==> The most common use of cat is to read the contents of files,
9) grep ==> filter a certain string
10) -i ==> tells the system to search for a file unspecific of capitalization



tail -f 
taill -100
grep -e/E/i ..etc

sudo -i
ls
tail -f
vi
vE



ssh -I admaximtest.pem nabeel@ec2-52-66-65-66.ap-south-1.compute.amazonaws.com
This week only do 
1) Monodb + ElasticSearch + kafka (Only read)
-------------------
Kafka + Stream
-------------------
https://docs.spring.io/spring-data/mongodb/docs/current/reference/html/
https://docs.mongodb.com/manual/tutorial/getting-started/
-------------------
https://www.elastic.co/guide/index.html
https://docs.spring.io/spring-data/elasticsearch/docs/current/reference/html/


username:- nabeel.amd93@gmail.com
password:- P@kistan
url:- https://dashboard.heroku.com


https://www.youtube.com/watch?v=zabc2uE0zZw


1) pwd => show the current directory
2) ls => show the contents of a directory with "ls"
3) ls -a => show all hidden file 
4) cd => used for move to directory (example:- cd filename, cd file\name)
5) mkdir&rmdir => mkdir mean create the new folder, while rmdir mean remove the folder
6) rm => rm command used to delete files or directory ('rm hello.py', for directory used 'rm -r folder')
7) touch => used command for create file(touch hello.py)
8) cp => copy file through command line, takes two argument(cp file/path destination) => example (cp hello.py /hello/pakistan/)
9) cat => cat used to read the contents of file
10) tail => command used to display the last 10 line
11) head => command used to display the first 10 line
11) grep => filter the certain string
12) -i => case in-sensitive help
13) *,+ => *signifies zero, one, more while +signifies one or more
---------------------------------------
BRE: Basic Regular Expressions -G
ERE: Extended Regular Expressions -E
PRCE: Perl Regular Expressions -P
----------------------------------------
read all content of file
1) cat filename.txt
2) grep -E 'i|a' filename.txt
3) grep -E -i 'i|a' filename.txt
4) grep -E 't*' filename.txt
5) grep -E 't+' filename.txt
----------------------------------------


