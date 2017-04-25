INSERT INTO `snow`.`COMPANY` 
(id,ein,name,address,city,state,phone,country,zipcode,sic_code,status,consumer_rating,company_type,email_address) 
VALUES (0 ,0,'Boeing' ,'1111 5th AVE','Chicago','IL','888 999-0000', 'USA','60290',1555 ,1 ,0,1 ,'test@boeing.com' );

INSERT INTO `snow`.`COMPANY` 
(id,ein,name,address,city,state,phone,country,zipcode,sic_code,status,consumer_rating,company_type,email_address) 
VALUES (1 ,1,'GM' ,'4444 5th AVE','Atlanta','IL','888 999-0000', 'USA','30301',4345 ,1 ,0,1 ,'test@gm.com' );

INSERT INTO `snow`.`COMPANY` 
(id,ein,name,address,city,state,phone,country,zipcode,sic_code,status,consumer_rating,company_type,email_address) 
VALUES (2 ,2,'Starbucks' ,'4444 5th AVE','Seatle','WA','888 999-0000', 'USA','98105',4345 ,1,0,1 ,'test@starbucks.com' );

INSERT INTO `snow`.`COMPANY` 
(id,ein,name,address,city,state,phone,country,zipcode,sic_code,status,consumer_rating,company_type,email_address) 
VALUES (3 ,3,'Bush Wacker Landscaping' ,'1111 5th AVE','Seatle','WA','888 999-0000', 'USA','98144',4345 ,1,3,2 ,'test@bushwacker.com' );

INSERT INTO `snow`.`COMPANY` 
(id,ein,name,address,city,state,phone,country,zipcode,sic_code,status,consumer_rating,company_type,email_address) 
VALUES (4 ,4,'Dynamic Lawn Service' ,'3333 5th AVE','Everett','WA','888 999-0000', 'USA','98203',4345 ,1,4,2 ,'test@bushwacker.com' );

INSERT INTO `snow`.`COMPANY` 
(id,ein,name,address,city,state,phone,country,zipcode,sic_code,status,consumer_rating,company_type,email_address) 
VALUES (5 ,5,'Snowbiz Inc of America' ,'2500 W 36th St','Chicago','IL','888 999-0000', 'USA','60632',4345 ,1 ,4,2 ,'test@bsnowbiz.com' );



INSERT INTO `snow`.`USERS` (user_id,password,email_address,user_role,activation_code,status,invalid_login,signup_time,first_name,last_name,company_id) VALUES ('admin@snow.com','E96387B8509FE1377B48D15480F03707F8530F65','demo@examqueen.com',2,'12345678',1,0,null,'','',0);
INSERT INTO `snow`.`USERS` (user_id,password,email_address,user_role,activation_code,status,invalid_login,signup_time,first_name,last_name,company_id) VALUES ('client1@snow.com','E96387B8509FE1377B48D15480F03707F8530F65','client1@hotmail.com',0,'942548035',0,2,null,'JAMES','DOE',0);
INSERT INTO `snow`.`USERS` (user_id,password,email_address,user_role,activation_code,status,invalid_login,signup_time,first_name,last_name,company_id) VALUES ('vendor1@snow.com','E96387B8509FE1377B48D15480F03707F8530F65','amid1122@hotmail.com',1,'942548035',0,2,null,'JAMES','DOE',5);


#mongodb
client = 
{ 
	"name" : "Boeing",
	"status": "1",
	"addresses" :
		{ 
		"street" : "1111 5th AVE",
		"city" : "Chicago",
		"state" : "IL",
		"phone" : "888 999-0000",
		"zipcode" : "60290"
		}
};

client2 = 
{ 
	"name" : "GM",
	"status": "1",
	"addresses" :
		{ 
		"street" : "4444 5th AVE",
		"city" : "Atlanta",
		"state" : "GA",
		"phone" : "888 999-0000",
		"zipcode" : "30301"
		}
};

vendor1 = 
{ 
	"name" : "Bush Wacker Landscaping",
	"status": "1",
	"consumer_rating" : "3",
	"addresses" : [ 
		{ 
		"street" : "4444 5th AVE",
		"city" : "Seattle",
		"state" : "WA",
		"phone" : "888 999-0000",
		"zipcode" : "98144"
		}
	]
};

vendor2 = 
{ 
	"name" : "Dynamic Lawn Service",
	"status": "1",
	"consumer_rating" : "3",
	"addresses" : [ 
		{ 
		"street" : "3333 5th AVE','Everett",
		"city" : "Everett",
		"state" : "WA",
		"phone" : "888 999-0000",
		"zipcode" : "98203"
		}
	]
};

user1 = {
"user_id" : "client@test.com",
"password" : "123",
"user_role" : "1",
"first_name" : "Jone",
"last_name" : "Doe",
"company_name" : "GM",
"email_address" : "test@test.com"
};

user2 = {
"user_id" : "vendor@test.com",
"password" : "123",
"user_role" : "2",
"first_name" : "Jone",
"last_name" : "Doe",
"company_name" : "Bush Wacker Landscaping",
"email_address" : "test2@test.com"
};

user3 = {
"user_id" : "admin@test.com",
"password" : "123",
"user_role" : "2",
"first_name" : "Jone",
"last_name" : "Doe",
"company_name" : "",
"email_address" : "test2@test.com"
};

