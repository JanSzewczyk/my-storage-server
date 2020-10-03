--
-- storage_item_view
--
create or replace view storage_item_view as
select
    i.id as item_id,
    i.amount as amount,
    p.id as product_id,
    p.name as product_name,
    o.currency as currency,
    p.description as product_description,
    p.value as value,
    (p.value * i.amount) as total_value,
    s.id as storage_id
from item i
    inner join product p on i.product_id = p.id
    inner join storage s on i.storage_id = s.id
    inner join owner o on s.owner_id = o.id;

--
-- employee_view
--
create or replace view employee_view as
select
    e.id as id,
    e.short_id as short_id,
    e.email as email,
    (e.first_name || ' ' || e.last_name) as name,
    e.phone as phone,
    e.address_city as address_city,
    e.address_country as address_country,
    e.address_street as address_street,
    e.address_zip as address_zip,
    e.updated_at as updated_at,
    e.created_at as created_at,
    s.id as storage_id,
    s.name as storage_name,
    o.id as owner_id,
    o.email as owner_email
from employee e
         left join storage s on e.storage_id = s.id
         inner join owner o on e.owner_id = o.id;

--
-- storage_view
--
create or replace view storage_view as
select
       s.id                 as id,
       s.short_id           as short_id,
       s.name               as name,
       s.surface            as surface,
       s.address_street     as address_street,
       s.address_city       as address_city,
       s.address_zip        as address_zip,
       s.address_country    as address_country,
       (
            select count(*) as number_of_employees
            from employee e
            where e.storage_id = s.id
        ),
       s.created_at         as created_at,
       s.updated_at         as updated_at,
       o.id                 as owner_id,
       a.last_action_date   as last_action_date
from storage s
    left join owner o on s.owner_id = o.id
    left join  (
        select max(a.created_at) as last_action_date ,storage_id
        from action a
        group by a.storage_id
    ) as a on a.storage_id = s.id;
