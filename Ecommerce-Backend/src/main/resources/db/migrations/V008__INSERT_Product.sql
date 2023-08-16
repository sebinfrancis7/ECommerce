insert
into
    categories
    (name)
values
    ('electronics');

insert
into
    products
    (name, description, imgurl, price, quantity, createdDateAndTime, category_id_fk )
values
    ('iphone 13', 'There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words believable','https://www.shutterstock.com/image-vector/minimalist-modern-colored-clay-mockup-600w-1739406437.jpg' ,60000,100,'2023-02-10 15:21:59.96299',1);
