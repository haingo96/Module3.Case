create database houserentingdb;
use houserentingdb;

CREATE TABLE Address
(
    province INT NOT NULL,
    district INT NOT NULL,
    ward INT NOT NULL,
    address_id INT NOT NULL,
    PRIMARY KEY (address_id)
);

CREATE TABLE RoleTable
(
    role_id INT NOT NULL,
    role_name INT NOT NULL,
    PRIMARY KEY (role_id)
);

CREATE TABLE User
(
    user_id INT NOT NULL,
    name INT NOT NULL,
    age INT NOT NULL,
    phone_number INT NOT NULL,
    identity_number INT NOT NULL,
    email INT NOT NULL,
    user_name INT NOT NULL,
    password INT NOT NULL,
    role_id INT NOT NULL,
    PRIMARY KEY (user_id),
    FOREIGN KEY (role_id) REFERENCES RoleTable(role_id)
);

CREATE TABLE House
(
    price INT NOT NULL,
    view_date DATE NOT NULL,
    unavaliable_until DATE NOT NULL,
    area INT NOT NULL,
    type INT NOT NULL,
    house_id INT NOT NULL,
    status INT NOT NULL,
    address_id INT NOT NULL,
    renter_id INT NOT NULL,
    owner_id INT NOT NULL,
    discription varchar(255),
    PRIMARY KEY (house_id),
    FOREIGN KEY (address_id) REFERENCES Address(address_id),
    FOREIGN KEY (renter_id) REFERENCES User(user_id),
    FOREIGN KEY (owner_id) REFERENCES User(user_id)
);

CREATE TABLE ImageURL
(
    image_id INT NOT NULL,
    image_name INT NOT NULL,
    image_path INT NOT NULL,
    house_id INT NOT NULL,
    PRIMARY KEY (image_id),
    FOREIGN KEY (house_id) REFERENCES House(house_id)
);

CREATE TABLE Review
(
    review_id INT NOT NULL,
    comment INT NOT NULL,
    rating INT NOT NULL,
    house_id INT NOT NULL,
    PRIMARY KEY (review_id),
    FOREIGN KEY (house_id) REFERENCES House(house_id)
);