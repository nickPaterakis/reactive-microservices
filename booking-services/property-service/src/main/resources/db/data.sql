use property_db;

insert ignore into countries values (1, "Greece");
insert ignore into countries values (2, "Germany");
insert ignore into countries values (3, "Hungary");
insert ignore into countries values (4, "Georgia");
insert ignore into countries values (5, "Italy");
insert ignore into countries values (6, "Spain");
insert ignore into countries values (7, "England");
insert ignore into countries values (8, "France");

insert ignore into property_types values (1, "House");
insert ignore into property_types values (2, "Apartment");
insert ignore into property_types values (3, "Secondary unit");
insert ignore into property_types values (4, "Unique Space");
insert ignore into property_types values (5, "Bed and breakfast");

insert ignore into guest_spaces values (1, "Entire place");
insert ignore into guest_spaces values (2, "Private room");
insert ignore into guest_spaces values (3, "Shared room");

insert ignore into addresses values (1,"Athens", 1, "01313" ,"filoloaou", 20);
insert ignore into addresses values (2,"Berlin", 2, "01012" ,"An der Windmühle", 4);
insert ignore into addresses values (3,"Budapest", 3, "01613" ,"Church Road", 4);
insert ignore into addresses values (4,"Tbilisi", 4, "01323" ,"Main Street", 45);
insert ignore into addresses values (5,"Rome", 5, "01317" ,"Springfield Road", 3);
insert ignore into addresses values (6,"Madrid", 6, "01213" ,"Kingsway", 5);
insert ignore into addresses values (7,"London", 7, "01813" ,"Victoria Road", 14);
insert ignore into addresses values (8,"Paris", 8, "01343" ,"High Street", 10);
insert ignore into addresses values (9,"Athens", 1, "01313" ,"filoloaou", 43);
insert ignore into addresses values (10,"Athens", 1, "01413" ,"filoloaou", 42);
insert ignore into addresses values (11,"Athens", 1, "01513" ,"filoloaou", 95);
insert ignore into addresses values (12,"Athens", 1, "01363" ,"filoloaou", 38);
insert ignore into addresses values (13,"Athens", 1, "01353" ,"filoloaou", 21);
insert ignore into addresses values (14,"Athens", 1, "01213" ,"filoloaou", 28);
insert ignore into addresses values (15,"Athens", 1, "01113" ,"filoloaou", 48);
insert ignore into addresses values (16,"Athens", 1, "01353" ,"filoloaou", 23);
insert ignore into addresses values (17,"Athens", 1, "01363" ,"filoloaou", 19);



-- insert ignore into owners values ("76393fab-10b2-40bb-b3ef-b75a76829178", "Nicholas", "Paterakis");

insert ignore into properties (id, title, property_type_id, guest_space_id, max_guest_number, bedroom_number, bath_number, price_per_night, address_id, owner) 
	values (1, "Luxurious flat in central Athens", 1, 1, 5, 2, 1, 30, 1, "76393fab-10b2-40bb-b3ef-b75a76829178");
insert ignore into properties (id, title, property_type_id, guest_space_id, max_guest_number, bedroom_number, bath_number, price_per_night, address_id, owner) 
	values (2, "80 qm Apartment, Prenzlauer Benrg", 2, 1, 7, 2, 2, 170, 2, "76393fab-10b2-40bb-b3ef-b75a76829178");
insert ignore into properties (id, title, property_type_id, guest_space_id, max_guest_number, bedroom_number, bath_number, price_per_night, address_id, owner) 
	values (3,"Cutting Edge Design Apartment in the Heart of Budapest Downtown", 2, 1, 2, 1, 1, 50, 3, null);
insert ignore into properties (id, title, property_type_id, guest_space_id, max_guest_number, bedroom_number, bath_number, price_per_night, address_id, owner) 
	values (4, "Be Mate Plaza Espana Suite Terrace 601", 2, 2, 3, 1, 1, 209, 6, null);
insert ignore into properties (id, title, property_type_id, guest_space_id, max_guest_number, bedroom_number, bath_number, price_per_night, address_id, owner) 
	values (5, "80 qm Apartment, Prenzlauer Benrg", 3, 2, 3, 2, 2, 170, 2, null);
insert ignore into properties (id, title, property_type_id, guest_space_id, max_guest_number, bedroom_number, bath_number, price_per_night, address_id, owner) 
	values (6, "Luxurious flat in central Athens", 1, 1, 5, 2, 1, 30, 9, null);
insert ignore into properties (id, title, property_type_id, guest_space_id, max_guest_number, bedroom_number, bath_number, price_per_night, address_id, owner) 
	values (7, "Luxurious flat in central Athens", 1, 1, 5, 2, 1, 30, 10, null);
insert ignore into properties (id, title, property_type_id, guest_space_id, max_guest_number, bedroom_number, bath_number, price_per_night, address_id, owner) 
	values (8, "Luxurious flat in central Athens", 1, 1, 5, 2, 1, 30, 11, null);
insert ignore into properties (id, title, property_type_id, guest_space_id, max_guest_number, bedroom_number, bath_number, price_per_night, address_id, owner) 
	values (9, "Luxurious flat in central Athens", 1, 1, 5, 2, 1, 30, 12, null);
insert ignore into properties (id, title, property_type_id, guest_space_id, max_guest_number, bedroom_number, bath_number, price_per_night, address_id, owner) 
	values (10, "Luxurious flat in central Athens", 1, 1, 5, 2, 1, 30, 13, null);
insert ignore into properties (id, title, property_type_id, guest_space_id, max_guest_number, bedroom_number, bath_number, price_per_night, address_id, owner) 
	values (11, "Luxurious flat in central Athens", 1, 1, 5, 2, 1, 30, 14, null);
insert ignore into properties (id, title, property_type_id, guest_space_id, max_guest_number, bedroom_number, bath_number, price_per_night, address_id, owner) 
	values (12, "Luxurious flat in central Athens", 1, 1, 5, 2, 1, 30, 15, null);
insert ignore into properties (id, title, property_type_id, guest_space_id, max_guest_number, bedroom_number, bath_number, price_per_night, address_id, owner) 
	values (13, "Luxurious flat in central Athens", 1, 1, 5, 2, 1, 30, 16, null);
insert ignore into properties (id, title, property_type_id, guest_space_id, max_guest_number, bedroom_number, bath_number, price_per_night, address_id, owner) 
	values (14, "Luxurious flat in central Athens", 1, 1, 5, 2, 1, 30, 17, null);

insert ignore into images (id, name, property_id) 
	values (1, "Spain/madrid/1/12cb3f49-f25f-42b1-9d68-697496811fc9.jpg", 4);
insert ignore into images (id, name, property_id) 
	values (2, "Greece/athens/1/b3d49bbd-d287-44e3-a938-07a8721263f4.jpg", 1);
insert ignore into images (id, name, property_id) 
	values (3, "Germany/berlin/1/483a9f42-c08e-46e7-b28a-149052cbca6b.jpg", 2);
insert ignore into images (id, name, property_id) 
	values (4, "Hungary/budapest/1/72301aa8-696d-4a61-8f97-850da90e0042.jpg", 3);
insert ignore into images (id, name, property_id) 
	values (5, "Germany/berlin/1/483a9f42-c08e-46e7-b28a-149052cbca6b.jpg", 5);
insert ignore into images (id, name, property_id) 
	values (6, "Spain/madrid/1/12cb3f49-f25f-42b1-9d68-697496811fc9.jpg", 6);
insert ignore into images (id, name, property_id) 
	values (7, "Spain/madrid/1/12cb3f49-f25f-42b1-9d68-697496811fc9.jpg", 7);
insert ignore into images (id, name, property_id) 
	values (8, "Spain/madrid/1/12cb3f49-f25f-42b1-9d68-697496811fc9.jpg", 8);
insert ignore into images (id, name, property_id) 
	values (9, "Spain/madrid/1/12cb3f49-f25f-42b1-9d68-697496811fc9.jpg", 9);
insert ignore into images (id, name, property_id) 
	values (10, "Spain/madrid/1/12cb3f49-f25f-42b1-9d68-697496811fc9.jpg", 10);
insert ignore into images (id, name, property_id) 
	values (11, "Spain/madrid/1/12cb3f49-f25f-42b1-9d68-697496811fc9.jpg", 11);
insert ignore into images (id, name, property_id) 
	values (12, "Spain/madrid/1/12cb3f49-f25f-42b1-9d68-697496811fc9.jpg", 12);
insert ignore into images (id, name, property_id) 
	values (13, "Spain/madrid/1/12cb3f49-f25f-42b1-9d68-697496811fc9.jpg", 13);
    
insert ignore into reservations values (1, '2021-05-01', '2021-05-10', 1);
insert ignore into reservations values (2, '2021-05-01', '2021-05-10', 2);
insert ignore into reservations values (3, '2021-05-01', '2021-05-10', 3);
insert ignore into reservations values (4, '2021-05-01', '2021-05-10', 4);
insert ignore into reservations values (3, '2021-05-01', '2021-05-10', 5);
insert ignore into reservations values (4, '2021-05-01', '2021-05-10', 6);
insert ignore into reservations values (5, '2021-05-01', '2021-05-10', 7);
insert ignore into reservations values (6, '2021-05-01', '2021-05-10', 8);
insert ignore into reservations values (7, '2021-05-01', '2021-05-10', 9);
insert ignore into reservations values (8, '2021-05-01', '2021-05-10', 10);
insert ignore into reservations values (9, '2021-05-01', '2021-05-10', 11);
insert ignore into reservations values (10, '2021-05-01', '2021-05-10', 12);
insert ignore into reservations values (11, '2021-05-01', '2021-05-10', 13);

insert ignore into amenities values (1, "Essentials");
insert ignore into amenities values (2, "Wifi");
insert ignore into amenities values (3, "Heating");
insert ignore into amenities values (4, "Air conditioning");
insert ignore into amenities values (5, "Iron");

insert ignore into property_amenities (property_id, amenity_id) values (1, 1);
insert ignore into property_amenities (property_id, amenity_id) values (2, 1);
insert ignore into property_amenities (property_id, amenity_id) values (3, 1);
insert ignore into property_amenities (property_id, amenity_id) values (4, 1);
insert ignore into property_amenities (property_id, amenity_id) values (5, 1);
insert ignore into property_amenities (property_id, amenity_id) values (1, 2);
insert ignore into property_amenities (property_id, amenity_id) values (2, 2);
insert ignore into property_amenities (property_id, amenity_id) values (3, 2);
insert ignore into property_amenities (property_id, amenity_id) values (4, 2);
insert ignore into property_amenities (property_id, amenity_id) values (5, 2);
insert ignore into property_amenities (property_id, amenity_id) values (1, 3);
insert ignore into property_amenities (property_id, amenity_id) values (2, 3);
insert ignore into property_amenities (property_id, amenity_id) values (3, 3);
insert ignore into property_amenities (property_id, amenity_id) values (4, 3);
insert ignore into property_amenities (property_id, amenity_id) values (5, 3);
insert ignore into property_amenities (property_id, amenity_id) values (1, 4);
insert ignore into property_amenities (property_id, amenity_id) values (2, 5);
insert ignore into property_amenities (property_id, amenity_id) values (3, 4);
insert ignore into property_amenities (property_id, amenity_id) values (4, 5);
insert ignore into property_amenities (property_id, amenity_id) values (5, 4);
insert ignore into property_amenities (property_id, amenity_id) values (6, 1);
insert ignore into property_amenities (property_id, amenity_id) values (7, 1);
insert ignore into property_amenities (property_id, amenity_id) values (8, 1);
insert ignore into property_amenities (property_id, amenity_id) values (9, 1);
insert ignore into property_amenities (property_id, amenity_id) values (10, 1);
insert ignore into property_amenities (property_id, amenity_id) values (11, 1);
insert ignore into property_amenities (property_id, amenity_id) values (12, 1);
insert ignore into property_amenities (property_id, amenity_id) values (13, 1);
insert ignore into property_amenities (property_id, amenity_id) values (6, 2);
insert ignore into property_amenities (property_id, amenity_id) values (7, 2);
insert ignore into property_amenities (property_id, amenity_id) values (8, 2);
insert ignore into property_amenities (property_id, amenity_id) values (9, 2);
insert ignore into property_amenities (property_id, amenity_id) values (10, 4);
insert ignore into property_amenities (property_id, amenity_id) values (11, 4);
insert ignore into property_amenities (property_id, amenity_id) values (12, 4);
insert ignore into property_amenities (property_id, amenity_id) values (13, 4);