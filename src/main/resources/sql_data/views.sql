--
-- storage_item_view
--

create or replace view storage_item_view as
select
    i.id as item_id,
    i.amount as amount,
    p.id as product_id,
    p.name as product_name,
    p.description as product_description,
    p.value as value,
    (p.value * i.amount) as total_value,
    s.id as storage_id
from item i
    inner join product p on i.product_id = p.id
    inner join storage s on i.storage_id = s.id;