use property_db;

insert ignore into countries values ("GR", "Greece");
insert ignore into countries values ("DE", "Germany");
insert ignore into countries values ("HU", "Hungary");
insert ignore into countries values ("GE", "Georgia");
insert ignore into countries values ("IT", "Italy");
insert ignore into countries values ("ES", "Spain");
insert ignore into countries values ("GB", "United Kingdom");
insert ignore into countries values ("FR", "France");

insert ignore into property_types values (1, "House");
insert ignore into property_types values (2, "Apartment");
insert ignore into property_types values (3, "Secondary unit");
insert ignore into property_types values (4, "Unique Space");
insert ignore into property_types values (5, "Bed and breakfast");

insert ignore into guest_spaces values (1, "Entire place");
insert ignore into guest_spaces values (2, "Private room");
insert ignore into guest_spaces values (3, "Shared room");

-- insert ignore into addresses values (1,"Athens", "GR", "01313" ,"filoloaou", 20);
-- insert ignore into addresses values (2,"Berlin", "DE", "01012" ,"An der Windmühle", 4);
-- insert ignore into addresses values (3,"Budapest", "HU", "01613" ,"Church Road", 4);
-- insert ignore into addresses values (4,"Tbilisi", "DE", "01323" ,"Main Street", 4);
-- -- insert ignore into addresses values (5,"Rome", 5, "01317" ,"Springfield Road", 3);
-- insert ignore into addresses values (6,"Madrid", "ES", "01213" ,"Kingsway", 5);
-- -- insert ignore into addresses values (7,"London", 7, "01813" ,"Victoria Road", 14);
-- -- insert ignore into addresses values (8,"Paris", 8, "01343" ,"High Street", 10);
-- insert ignore into addresses values (9,"Athens", "GR", "01313" ,"filoloaou", 43);
-- insert ignore into addresses values (10,"Athens", "GR", "01413" ,"filoloaou", 42);
-- insert ignore into addresses values (11,"Athens", "GR", "01513" ,"filoloaou", 95);
-- insert ignore into addresses values (12,"Athens", "GR", "01363" ,"filoloaou", 38);
-- insert ignore into addresses values (13,"Athens", "GR", "01353" ,"filoloaou", 21);
-- insert ignore into addresses values (14,"Athens", "GR", "01213" ,"filoloaou", 28);
-- insert ignore into addresses values (15,"Athens", "GR", "01113" ,"filoloaou", 48);
-- insert ignore into addresses values (16,"Athens", "GR", "01353" ,"filoloaou", 23);
-- insert ignore into addresses values (17,"Athens", "GR", "01363" ,"filoloaou", 19);

-- insert ignore into owners values ("698ad56e-44c4-4c0e-b3ca-f1e8bb4f533f", "Nicholas", "Paterakis");

-- insert ignore into properties (id, title, description, property_type_id, guest_space_id, max_guest_number, bedroom_number, bath_number, price_per_night, address_id, owner)
-- 	values (1, "Luxurious flat in central Athens",
--     "Quisque id dolor pharetra tortor eleifend accumsan. Vivamus orci metus, fringilla suscipit ligula sed,
--     vestibulum aliquet enim. Sed blandit euismod diam eu egestas. Duis vulputate, nisl quis dapibus tincidunt,
--     dolor lorem maximus ipsum, lobortis mattis ante arcu at justo. Mauris rutrum consequat turpis
--     , in viverra nisi egestas scelerisque",
--     1, 1, 5, 2, 1, 30, 1, "79a4375e-386a-4352-8667-3c6007a6e6a4");
-- insert ignore into properties (id, title, description, property_type_id, guest_space_id, max_guest_number, bedroom_number, bath_number, price_per_night, address_id, owner)
-- 	values (2, "80 qm Apartment, Prenzlauer Benrg",
--     "Quisque id dolor pharetra tortor eleifend accumsan. Vivamus orci metus, fringilla suscipit ligula sed,
--     vestibulum aliquet enim. Sed blandit euismod diam eu egestas. Duis vulputate, nisl quis dapibus tincidunt,
--     dolor lorem maximus ipsum, lobortis mattis ante arcu at justo. Mauris rutrum consequat turpis
--     , in viverra nisi egestas scelerisque",
--     2, 1, 7, 2, 2, 170, 2, "79a4375e-386a-4352-8667-3c6007a6e6a4");
-- insert ignore into properties (id, title, description, property_type_id, guest_space_id, max_guest_number, bedroom_number, bath_number, price_per_night, address_id, owner)
-- 	values (3,"Cutting Edge Design Apartment in the Heart of Budapest Downtown",
--     "Quisque id dolor pharetra tortor eleifend accumsan. Vivamus orci metus, fringilla suscipit ligula sed,
--     vestibulum aliquet enim. Sed blandit euismod diam eu egestas. Duis vulputate, nisl quis dapibus tincidunt,
--     dolor lorem maximus ipsum, lobortis mattis ante arcu at justo. Mauris rutrum consequat turpis
--     , in viverra nisi egestas scelerisque",
--     2, 1, 2, 1, 1, 50, 3, "79a4375e-386a-4352-8667-3c6007a6e6a4");
-- insert ignore into properties (id, title, description, property_type_id, guest_space_id, max_guest_number, bedroom_number, bath_number, price_per_night, address_id, owner)
-- 	values (4, "Be Mate Plaza Espana Suite Terrace 601",
--     "Quisque id dolor pharetra tortor eleifend accumsan. Vivamus orci metus, fringilla suscipit ligula sed,
--     vestibulum aliquet enim. Sed blandit euismod diam eu egestas. Duis vulputate, nisl quis dapibus tincidunt,
--     dolor lorem maximus ipsum, lobortis mattis ante arcu at justo. Mauris rutrum consequat turpis
--     , in viverra nisi egestas scelerisque",
--     2, 2, 3, 1, 1, 209, 6, "79a4375e-386a-4352-8667-3c6007a6e6a4");
-- insert ignore into properties (id, title, description, property_type_id, guest_space_id, max_guest_number, bedroom_number, bath_number, price_per_night, address_id, owner)
-- 	values (5, "80 qm Apartment, Prenzlauer Benrg",
--     "Quisque id dolor pharetra tortor eleifend accumsan. Vivamus orci metus, fringilla suscipit ligula sed,
--     vestibulum aliquet enim. Sed blandit euismod diam eu egestas. Duis vulputate, nisl quis dapibus tincidunt,
--     dolor lorem maximus ipsum, lobortis mattis ante arcu at justo. Mauris rutrum consequat turpis
--     , in viverra nisi egestas scelerisque",
--     3, 2, 3, 2, 2, 170, 4, "79a4375e-386a-4352-8667-3c6007a6e6a4");
-- insert ignore into properties (id, title, description, property_type_id, guest_space_id, max_guest_number, bedroom_number, bath_number, price_per_night, address_id, owner)
-- 	values (6, "Luxurious flat in central Athens",
--     "Quisque id dolor pharetra tortor eleifend accumsan. Vivamus orci metus, fringilla suscipit ligula sed,
--     vestibulum aliquet enim. Sed blandit euismod diam eu egestas. Duis vulputate, nisl quis dapibus tincidunt,
--     dolor lorem maximus ipsum, lobortis mattis ante arcu at justo. Mauris rutrum consequat turpis
--     , in viverra nisi egestas scelerisque",
--     1, 1, 5, 2, 1, 30, 9, "79a4375e-386a-4352-8667-3c6007a6e6a4");
-- insert ignore into properties (id, title, description, property_type_id, guest_space_id, max_guest_number, bedroom_number, bath_number, price_per_night, address_id, owner)
-- 	values (7, "Luxurious flat in central Athens",
--     "Quisque id dolor pharetra tortor eleifend accumsan. Vivamus orci metus, fringilla suscipit ligula sed,
--     vestibulum aliquet enim. Sed blandit euismod diam eu egestas. Duis vulputate, nisl quis dapibus tincidunt,
--     dolor lorem maximus ipsum, lobortis mattis ante arcu at justo. Mauris rutrum consequat turpis
--     , in viverra nisi egestas scelerisque",
--     1, 1, 5, 2, 1, 30, 10, "79a4375e-386a-4352-8667-3c6007a6e6a4");
-- insert ignore into properties (id, title, description, property_type_id, guest_space_id, max_guest_number, bedroom_number, bath_number, price_per_night, address_id, owner)
-- 	values (8, "Luxurious flat in central Athens",
--     "Quisque id dolor pharetra tortor eleifend accumsan. Vivamus orci metus, fringilla suscipit ligula sed,
--     vestibulum aliquet enim. Sed blandit euismod diam eu egestas. Duis vulputate, nisl quis dapibus tincidunt,
--     dolor lorem maximus ipsum, lobortis mattis ante arcu at justo. Mauris rutrum consequat turpis
--     , in viverra nisi egestas scelerisque",
--     1, 1, 5, 2, 1, 30, 11, "79a4375e-386a-4352-8667-3c6007a6e6a4");
-- insert ignore into properties (id, title, description, property_type_id, guest_space_id, max_guest_number, bedroom_number, bath_number, price_per_night, address_id, owner)
-- 	values (9, "Luxurious flat in central Athens",
--     "Quisque id dolor pharetra tortor eleifend accumsan. Vivamus orci metus, fringilla suscipit ligula sed,
--     vestibulum aliquet enim. Sed blandit euismod diam eu egestas. Duis vulputate, nisl quis dapibus tincidunt,
--     dolor lorem maximus ipsum, lobortis mattis ante arcu at justo. Mauris rutrum consequat turpis
--     , in viverra nisi egestas scelerisque",
--     1, 1, 5, 2, 1, 30, 12, "79a4375e-386a-4352-8667-3c6007a6e6a4");
-- insert ignore into properties (id, title, description, property_type_id, guest_space_id, max_guest_number, bedroom_number, bath_number, price_per_night, address_id, owner)
-- 	values (10, "Luxurious flat in central Athens",
--     "Quisque id dolor pharetra tortor eleifend accumsan. Vivamus orci metus, fringilla suscipit ligula sed,
--     vestibulum aliquet enim. Sed blandit euismod diam eu egestas. Duis vulputate, nisl quis dapibus tincidunt,
--     dolor lorem maximus ipsum, lobortis mattis ante arcu at justo. Mauris rutrum consequat turpis
--     , in viverra nisi egestas scelerisque",
--     1, 1, 5, 2, 1, 30, 13, "79a4375e-386a-4352-8667-3c6007a6e6a4");
-- insert ignore into properties (id, title, description, property_type_id, guest_space_id, max_guest_number, bedroom_number, bath_number, price_per_night, address_id, owner)
-- 	values (11, "Luxurious flat in central Athens",
--     "Quisque id dolor pharetra tortor eleifend accumsan. Vivamus orci metus, fringilla suscipit ligula sed,
--     vestibulum aliquet enim. Sed blandit euismod diam eu egestas. Duis vulputate, nisl quis dapibus tincidunt,
--     dolor lorem maximus ipsum, lobortis mattis ante arcu at justo. Mauris rutrum consequat turpis
--     , in viverra nisi egestas scelerisque",
--     1, 1, 5, 2, 1, 30, 14, "79a4375e-386a-4352-8667-3c6007a6e6a4");
-- insert ignore into properties (id, title, description, property_type_id, guest_space_id, max_guest_number, bedroom_number, bath_number, price_per_night, address_id, owner)
-- 	values (12, "Luxurious flat in central Athens",
--     "Quisque id dolor pharetra tortor eleifend accumsan. Vivamus orci metus, fringilla suscipit ligula sed,
--     vestibulum aliquet enim. Sed blandit euismod diam eu egestas. Duis vulputate, nisl quis dapibus tincidunt,
--     dolor lorem maximus ipsum, lobortis mattis ante arcu at justo. Mauris rutrum consequat turpis
--     , in viverra nisi egestas scelerisque",
--     1, 1, 5, 2, 1, 30, 15, "79a4375e-386a-4352-8667-3c6007a6e6a4");
-- insert ignore into properties (id, title, description, property_type_id, guest_space_id, max_guest_number, bedroom_number, bath_number, price_per_night, address_id, owner)
-- 	values (13, "Luxurious flat in central Athens",
--     "Quisque id dolor pharetra tortor eleifend accumsan. Vivamus orci metus, fringilla suscipit ligula sed,
--     vestibulum aliquet enim. Sed blandit euismod diam eu egestas. Duis vulputate, nisl quis dapibus tincidunt,
--     dolor lorem maximus ipsum, lobortis mattis ante arcu at justo. Mauris rutrum consequat turpis
--     , in viverra nisi egestas scelerisque",
--     1, 1, 5, 2, 1, 30, 16, "79a4375e-386a-4352-8667-3c6007a6e6a4");
-- insert ignore into properties (id, title, description, property_type_id, guest_space_id, max_guest_number, bedroom_number, bath_number, price_per_night, address_id, owner)
-- 	values (14, "Luxurious flat in central Athens",
--     "Quisque id dolor pharetra tortor eleifend accumsan. Vivamus orci metus, fringilla suscipit ligula sed,
--     vestibulum aliquet enim. Sed blandit euismod diam eu egestas. Duis vulputate, nisl quis dapibus tincidunt,
--     dolor lorem maximus ipsum, lobortis mattis ante arcu at justo. Mauris rutrum consequat turpis
--     , in viverra nisi egestas scelerisque",
--     1, 1, 5, 2, 1, 30, 17, "79a4375e-386a-4352-8667-3c6007a6e6a4");
--
-- insert ignore into images (id, name, property_id)
-- 	values (1, "\\France\\79a4375e-386a-4352-8667-3c6007a6e6a4\\0d5529aa-ec4a-4439-868f-feb24ad4a510\\37ec254a-65ce-4f8b-ad23-a423574cedf6.jpg", 4);
-- insert ignore into images (id, name, property_id)
-- 	values (2, "\\Greece\\79a4375e-386a-4352-8667-3c6007a6e6a4\\546f154b-a042-46e3-af3c-588e660e00e6\\07bf9032-8d53-4715-ab5b-2e5eb5f9dd78.jpg", 1);
-- insert ignore into images (id, name, property_id)
-- 	values (3, "\\Greece\\79a4375e-386a-4352-8667-3c6007a6e6a4\\546f154b-a042-46e3-af3c-588e660e00e6\\07bf9032-8d53-4715-ab5b-2e5eb5f9dd78.jpg", 2);
-- insert ignore into images (id, name, property_id)
-- 	values (4, "\\Greece\\79a4375e-386a-4352-8667-3c6007a6e6a4\\546f154b-a042-46e3-af3c-588e660e00e6\\07bf9032-8d53-4715-ab5b-2e5eb5f9dd78.jpg", 3);
-- insert ignore into images (id, name, property_id)
-- 	values (5, "\\Greece\\79a4375e-386a-4352-8667-3c6007a6e6a4\\546f154b-a042-46e3-af3c-588e660e00e6\\07bf9032-8d53-4715-ab5b-2e5eb5f9dd78.jpg", 5);
-- insert ignore into images (id, name, property_id)
-- 	values (6, "\\France\\79a4375e-386a-4352-8667-3c6007a6e6a4\\0d5529aa-ec4a-4439-868f-feb24ad4a510\\37ec254a-65ce-4f8b-ad23-a423574cedf6.jpg", 6);
-- insert ignore into images (id, name, property_id)
-- 	values (7, "\\France\\79a4375e-386a-4352-8667-3c6007a6e6a4\\0d5529aa-ec4a-4439-868f-feb24ad4a510\\37ec254a-65ce-4f8b-ad23-a423574cedf6.jpg", 7);
-- insert ignore into images (id, name, property_id)
-- 	values (8, "\\France\\79a4375e-386a-4352-8667-3c6007a6e6a4\\0d5529aa-ec4a-4439-868f-feb24ad4a510\\37ec254a-65ce-4f8b-ad23-a423574cedf6.jpg", 8);
-- insert ignore into images (id, name, property_id)
-- 	values (9, "\\France\\79a4375e-386a-4352-8667-3c6007a6e6a4\\0d5529aa-ec4a-4439-868f-feb24ad4a510\\37ec254a-65ce-4f8b-ad23-a423574cedf6.jpg", 9);
-- insert ignore into images (id, name, property_id)
-- 	values (10, "\\France\\79a4375e-386a-4352-8667-3c6007a6e6a4\\0d5529aa-ec4a-4439-868f-feb24ad4a510\\37ec254a-65ce-4f8b-ad23-a423574cedf6.jpg", 10);
-- insert ignore into images (id, name, property_id)
-- 	values (11, "\\France\\79a4375e-386a-4352-8667-3c6007a6e6a4\\0d5529aa-ec4a-4439-868f-feb24ad4a510\\37ec254a-65ce-4f8b-ad23-a423574cedf6.jpg", 11);
-- insert ignore into images (id, name, property_id)
-- 	values (12, "\\France\\79a4375e-386a-4352-8667-3c6007a6e6a4\\0d5529aa-ec4a-4439-868f-feb24ad4a510\\37ec254a-65ce-4f8b-ad23-a423574cedf6.jpg", 12);
-- insert ignore into images (id, name, property_id)
-- 	values (13, "\\France\\79a4375e-386a-4352-8667-3c6007a6e6a4\\0d5529aa-ec4a-4439-868f-feb24ad4a510\\37ec254a-65ce-4f8b-ad23-a423574cedf6.jpg", 13);
-- 	insert ignore into images (id, name, property_id)
-- 	values (14, "\\France\\79a4375e-386a-4352-8667-3c6007a6e6a4\\0d5529aa-ec4a-4439-868f-feb24ad4a510\\37ec254a-65ce-4f8b-ad23-a423574cedf6.jpg", 14);

insert ignore into amenities values (1, "Essentials");
insert ignore into amenities values (2, "Wifi");
insert ignore into amenities values (3, "Heating");
insert ignore into amenities values (4, "Air conditioning");
insert ignore into amenities values (5, "Iron");
insert ignore into amenities values (6, "Hair Dryer");
insert ignore into amenities values (7, "Breakfast");
insert ignore into amenities values (8, "Coffee");
insert ignore into amenities values (9, "Tea");
insert ignore into amenities values (10, "Indoor Fireplaces");
--
-- insert ignore into property_amenities (property_id, amenity_id) values (1, 1);
-- insert ignore into property_amenities (property_id, amenity_id) values (2, 1);
-- insert ignore into property_amenities (property_id, amenity_id) values (3, 1);
-- insert ignore into property_amenities (property_id, amenity_id) values (4, 1);
-- insert ignore into property_amenities (property_id, amenity_id) values (5, 1);
-- insert ignore into property_amenities (property_id, amenity_id) values (1, 2);
-- insert ignore into property_amenities (property_id, amenity_id) values (2, 2);
-- insert ignore into property_amenities (property_id, amenity_id) values (3, 2);
-- insert ignore into property_amenities (property_id, amenity_id) values (4, 2);
-- insert ignore into property_amenities (property_id, amenity_id) values (5, 2);
-- insert ignore into property_amenities (property_id, amenity_id) values (1, 3);
-- insert ignore into property_amenities (property_id, amenity_id) values (2, 3);
-- insert ignore into property_amenities (property_id, amenity_id) values (3, 3);
-- insert ignore into property_amenities (property_id, amenity_id) values (4, 3);
-- insert ignore into property_amenities (property_id, amenity_id) values (5, 3);
-- insert ignore into property_amenities (property_id, amenity_id) values (1, 4);
-- insert ignore into property_amenities (property_id, amenity_id) values (2, 5);
-- insert ignore into property_amenities (property_id, amenity_id) values (3, 4);
-- insert ignore into property_amenities (property_id, amenity_id) values (4, 5);
-- insert ignore into property_amenities (property_id, amenity_id) values (5, 4);
-- insert ignore into property_amenities (property_id, amenity_id) values (6, 1);
-- insert ignore into property_amenities (property_id, amenity_id) values (7, 1);
-- insert ignore into property_amenities (property_id, amenity_id) values (8, 1);
-- insert ignore into property_amenities (property_id, amenity_id) values (9, 1);
-- insert ignore into property_amenities (property_id, amenity_id) values (10, 1);
-- insert ignore into property_amenities (property_id, amenity_id) values (11, 1);
-- insert ignore into property_amenities (property_id, amenity_id) values (12, 1);
-- insert ignore into property_amenities (property_id, amenity_id) values (13, 1);
-- insert ignore into property_amenities (property_id, amenity_id) values (6, 2);
-- insert ignore into property_amenities (property_id, amenity_id) values (7, 2);
-- insert ignore into property_amenities (property_id, amenity_id) values (8, 2);
-- insert ignore into property_amenities (property_id, amenity_id) values (9, 2);
-- insert ignore into property_amenities (property_id, amenity_id) values (10, 4);
-- insert ignore into property_amenities (property_id, amenity_id) values (11, 4);
-- insert ignore into property_amenities (property_id, amenity_id) values (12, 4);
-- insert ignore into property_amenities (property_id, amenity_id) values (13, 4);