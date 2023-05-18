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

 insert into catagory([name]) values
 ('Cloth and Cosmetics'),
 ('Cars'),
 ('Real Estate'),
 ('Other'),
 ('Food')

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

----------------------------------------------------------------------------------------------------