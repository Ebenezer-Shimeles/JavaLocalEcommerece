Use Ecomm;
create database Ecomm;
drop database Ecomm;
use master;
-----------------------user bloc----------------------------------------------------------------

drop table users;

create table users(id int primary key identity(1, 2), 
                   name varchar(30) not NULL, 
				   last_name varchar(30) not null, 
                   gender char(1) not NULL default 'M', 
				   email varchar(200) not null unique, 
				   password varchar(200) not null, 
                   age tinyint not null default 13, 
				   constraint AGE_CON check(age> 13),
				   balance money
);


/*
"Insert into users(name, email,last_name, gender, password, age, balance) "
    	    		+ "values('"+name+"','"+email+"' ,'"+lastName+"', '"+gender+"', '"+password+"', "+age+", 100 )
*/

---defining error messages here
EXEC sp_addmessage 
    @msgnum = 50001, 
    @severity = 1, 
    @msgtext = 'Invalid Email Address';

EXEC sp_addmessage 
    @msgnum = 50002, 
    @severity = 1, 
    @msgtext = 'Email is taken';

EXEC sp_addmessage 
    @msgnum = 50003, 
    @severity = 1, 
    @msgtext = 'Age must be at least 13 years old';


EXEC sp_addmessage 
    @msgnum = 50004, 
    @severity = 1, 
    @msgtext = 'Password is too short';

EXEC sp_addmessage 
    @msgnum = 50005, 
    @severity = 1, 
    @msgtext = 'Password is incorrect';



----
alter proc [login_user](@email varchar(30), @password varchar(30))
as
begin
  if (select count(*) from "users" where email=@email)  = 0
  begin
      raiserror('error no user found!', 15, 1)
	  return
  end
  declare @pass_in_db varchar(30)
  select top 1 @pass_in_db = "password" from [users] where email = @email
  if @pass_in_db != @password
  begin
     raiserror(50005, 15 ,10)
	 return
  end
  print concat(@pass_in_db, 'dsds');
   print('login successful');
end
select * from users;
exec [login_user] @email='Email', @password='Password';
exec [login_user] @email='e@b.com', @password='dsdsdsdsdssd'


alter proc [register_user](@name varchar(30), @lastName varchar(30), @gender varchar(1),
                            @email varchar(200), @password varchar(200), @age tinyint)
							as
begin
    if len(@password) < 6
	begin
	   raiserror(50004, 12, 1);
	   return;
	end
    if @age < 13
	begin
	   raiserror(50003, 12, -1);
	   return
	end
    if CHARINDEX('@', @email) = 0
	begin
	    print concat('@ is missing in', @email) ;
	    raiserror(50001, 12, 1);
		return
	end
	 if CHARINDEX('.', @email) = 0
	begin
	    print concat('. is missing in', @email) ;
	    raiserror(50001, 12, 1);
		return
	end
	if (select count(*) from users where email = @email) != 0
	begin
	    raiserror(50002, 12, 1);
		return
	end
	else
	begin
		Insert into users("name", email,last_name, gender, password, age, balance) 
    		values(@name, @email ,@lastName, @gender, @password, @age, 100);
	end

end
--test
select * from users;
exec [register_user] @name='dsds', @lastName='dsds', @gender='M', @email='e@b.com', @password='dsdsdsdefe383f0no3fnudlfndjfndfdfdd', @age=20;

exec [register_user] @name = 'dsds' ,@lastName= 'dsds' ,@gender='M' ,@email='dsds@dsds.com' ,@password='muzebelahu' ,@age=1



------------------------------------------------------------

--------------sales object bloc--------------------------------------------------------------------


EXEC sp_addmessage 
    @msgnum = 50006, 
    @severity = 1, 
    @msgtext = 'Owner Does not exist!!!!';
EXEC sp_addmessage 
    @msgnum = 50007, 
    @severity = 1, 
    @msgtext = 'Invalid quantity number given!';
EXEC sp_addmessage 
    @msgnum = 50008, 
    @severity = 1, 
    @msgtext = 'Invalid name given!';
EXEC sp_addmessage 
    @msgnum = 50009, 
    @severity = 1, 
    @msgtext = 'Invalid description given!';
EXEC sp_addmessage 
    @msgnum = 50010, 
    @severity = 1, 
    @msgtext = 'Invalid price given!';
EXEC sp_addmessage 
    @msgnum = 50011, 
    @severity = 1, 
    @msgtext = 'Category does not exist!';






---------msgs
create table objects(
           id int primary key identity, 
		   owner_id int foreign key references users(id) not null,
           "name" varchar(20) not null, 
		    brand varchar(20), 
			quantity int not null default 1, 
			descr varchar(MAX) ,
            price money not null
 );
 create table catagory(id int identity primary key, name varchar(100) not null);
 alter table objects add cata int foreign key references catagory(id);
 drop table catagory;
 alter table objects drop FK__objects__cata__3B75D760
  alter table objects drop column cata; 
 delete from objects;
 insert into catagory([name]) values
 ('Shoes and Cloth'),
 ('Beuty and Cosmetics'),
 ('Electronics'),
 ('Furniture'),
 ('Custom Made'),
 ('Other')
 select * from objects;

 alter table objects add is_deleted char(1) default 'N'; 

 select * from objects;
 exec [add_sale_object] @ownerId = 13, @name ='N', @brand = 'Brand', @quantity =1, @descr = 'dsdsdsdsds', @price =1.0, @cata = 1
 exec [add_sale_object] @ownerId =1, @name ='Shoes', @brand ='dsds', @quantity =10,
                             @descr ='dsddsdsdsdsd', @price =10, @cata =1;


 alter proc add_sale_object(@ownerId int, @name varchar(20), @brand varchar(20), @quantity int,
                             @descr varchar(MAX), @price money, @cata int 
 )
 as 
 begin
      if (select top 1 count(*) from [users] where id = @ownerId) = 0
	  begin
	      raiserror(50006, 12, -1);
		  return;
	  end
	  if  @quantity <= 0
	  begin
	      raiserror(50007, 12, -1);
		  return;
	  end
	  if len(@name) <= 3
	  begin
	       raiserror(50008, 12, -1);
		  return;
	  end
	  if len(@descr) <= 8
	  begin
	       raiserror(50009, 12, -1);
		  return;
	  end
	  if  @price <= 0
	  begin
	      raiserror(50010, 12, -1);
		  return;
	  end
	  if (select top 1 count(*) from [catagory] where id = @cata) = 0
	  begin
	      raiserror(50011, 12, -1);
		  return;
	  end

	 INSERT into objects(owner_id,[name],quantity, descr, price, brand , cata)
	 values(@ownerId, @name, @quantity, @descr, @price ,@brand, @cata)
	 print 'Registered object!';

 end



 alter function get_objects_of(@owner_id int)
 returns Table
 as return (select * from objects where owner_id = @owner_id and is_deleted = 'N') ;

 select * from dbo.get_objects_of(13)

 select * from objects;
 alter proc delete_object(@objectId int)
 as
 begin
      if (select count(*) from objects where id = @objectId) = 0
	  begin
	      raiserror('object does not exist!!!', 12, 1);
		  return;
	  end
	  update objects  set is_deleted = 'Y'  where id = @objectId;
 end


 ---cart mini section

 create table cart(id int primary key identity, obj_id int foreign key references objects(id) not null, 
                  owner_id int foreign key references users(id) not null);
select * from getCartOf(1);
select * from objects;

exec addInCart 13, 3
create function getCartOf(@ownerId int) returns table
as return (select * from cart where owner_id = @ownerId);

alter proc addInCart(@ownerId int, @objectId int)
as
begin 
    insert into cart(obj_id, owner_id) values( @objectId, @ownerId);
end

create proc removeFromCart(@ownerId int, @objectId int)
as
begin 
    delete from  cart where   obj_id = @objectId AND owner_id = @ownerId
end

alter function getActiveSaleObjectWithinRange(@min money =0, @max money, @searchTerm varchar(20)='', @userId int)
returns table
as  return (
         select * from active_objects where price >= @min and price <= @max and  
		 (owner_id != @userId) and
         (([name] LIKE concat('%', @searchTerm, '%') or brand like concat('%', @searchTerm, '%') or descr LIKE concat('%', @searchTerm, '%') )))


select * from dbo.getActiveSaleObjectWithinRange(0, 100000, '');
update objects set owner_id = 7;
create view active_objects as (select * from objects where is_deleted = 'N' and quantity >0);


create table transactions(id int primary key identity, buyer_id int foreign key references users(id) not null,
                          obj_id int foreign key references objects(id) not null);
alter table transactions add  ammount money not null;
create table comments(id int primary key identity, msg varchar(200) not null, from_user int foreign key references users(id), 
                      forobject int foreign key references objects(id));

create view objects_carts as select count(*) as add_no, objects.id from objects inner join cart on cart.obj_id = objects.id group by objects.id;

create view objects_comments as select count(*)as comment_no,  objects.id  from objects inner join comments on comments.forobject = objects.id group by objects.id;


----------------------------------------------------------------------------------------------------