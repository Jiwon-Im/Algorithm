
SELECT distinct a.cart_id
from cart_products a inner join cart_products b
                                on a.cart_id = b.cart_id
where a.name = 'Milk' and b.name = 'Yogurt'
order by cart_id