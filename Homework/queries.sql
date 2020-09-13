/*
1
*/

Select model, speed, hd
from PC
where price < 500

/*
2
*/

Select distinct maker
from product
where type = 'Printer'

/*
3
*/

Select model, ram, screen
from Laptop
where price > 1000

/*
4
*/

Select *
from Printer
where color = 'y'

/*
5
*/

Select model, speed, hd
from PC
where (cd = '12x' or cd = '24x') and price < 600

/*
6 ????????????????
*/

select distinct maker, speed from product
left join laptop on laptop.model = product.model
where hd >= 10

/*
7
*/

SELECT * FROM (SELECT model, price 
 FROM PC
 UNION
 SELECT model, price 
 FROM Laptop
 UNION
 SELECT model, price 
 FROM Printer
 ) AS a
WHERE a.model IN (SELECT model 
 FROM Product 
 WHERE maker = 'B'
 )

/*
8
*/

Select maker
from product
where type = 'PC'
except 
select maker
from product
where type = 'Laptop'

/*
9
*/

Select distinct maker
from product
where model in
(select model
from pc
where speed >= 450
)

/*
10
*/

Select model, price
from printer
where price in 
(
select max(price)
from printer
)

/*
11
*/

Select avg(speed)
from pc

/*
12
*/

Select avg(speed)
from laptop
where price > 1000

/*
13
*/

Select avg(speed)
from pc
where model in 
(
select model
from product
where maker = 'A'
)

/*
14
*/

select s.class, s.name, c.country
from ships s
left join classes c on s.class = c.class
where c.numguns >=10

/*
15
*/

Select distinct hd
from pc
group by hd
having count(hd)>=2

/*
16
*/

Select distinct a.model, b.model, a.speed, a.ram
from PC as a, PC as b
where a.model > b.model and a.speed = b.speed and a.ram = b.ram

/*
17
*/

Select distinct type, p.model, speed
from product p
left join laptop l on l.model = p.model
where speed < ( select min(speed) from pc)

/*
18
*/

Select distinct maker, price
from product
left join printer on product.model = printer.model
where price = (select min(price) from printer where color = 'y') and color = 'y'

/*
19
*/

Select distinct maker, avg(screen)
from product
left join laptop on product.model = laptop.model
where product.model in (select model from laptop)
group by maker

/*
20
*/

select maker, count(model)
from product
where type = 'PC'
group by maker
having count(model) >= 3

/*
21
*/

select maker, max(price)
from product
left join PC on pc.model = product.model
where product.model in (select model from pc)
group by maker

/*
22
*/

Select speed, avg(price)
from pc
where speed > 600
group by speed

/*
23
*/

Select maker
from product
where model in
(
select model
from PC
where speed >=750
)
intersect
select maker
from product
where model in
(
select model
from laptop
where speed >=750
)

/*
24
*/

select model from (
select model, price from pc
union
select model, price from laptop
union
select model, price from printer) as a
where price = (select max(price) from (select model, price from pc
union
select model, price from laptop
union
select model, price from printer) as a)

/*
25
*/

select distinct maker from product
where model in
(
select model from pc
where ram = (select min(ram) from pc)
and speed = (select max(speed) from  pc where ram = (select min(ram) from pc))) intersect 
select maker from product
where type = 'printer'

/*
26
*/

select avg(price) from
(
select model, price from pc
where model in
(
select model from product where maker = 'a'
)
union all
select model, price from laptop
where model in
(
select model from product where maker = 'a'
)
)as a

/*
27
*/

Select maker, avg(hd)
from product
left join pc on pc.model = product.model
where maker in 
(
select maker from product where type = 'printer'
)
and maker in
(
select maker from product where type = 'pc'
)

group by maker

/*
28
*/

select count(maker) from(
Select distinct maker from product
group by maker
having count(model) = 1
) as a

/*
29
*/

select q.point, q.date, sum(q.inc), sum(q.out) from
(select point, date, inc, null as out
from Income_o
union
select point, date, null as inc, out
from Outcome_o) as q
group by q.point, q.date

/*
30
*/

select point, date, sum(out) as out, sum(inc) as inc from
(
select point, date, sum(inc) as inc, null as out from Income
group by point, date
union
select point, date, null as inc, sum(out) as out from Outcome
group by point, date
) as r
group by point, date


