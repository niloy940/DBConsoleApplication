LIST_PRODUCTS{
    select * from product;
}

ADD_PRODUCT{
    insert into product
    (id, name, price) values(?, ?, ?);
}

GET_PRODUCT{
    select * from product where id = ?;
}

DELETE_PRODUCT{
    delete from product where id = ?;
}