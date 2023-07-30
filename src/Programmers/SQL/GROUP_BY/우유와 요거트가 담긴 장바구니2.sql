select cart_id from (
                        select cart_id, group_concat(name) name
                        from cart_products
                        group by cart_id
                    ) a
where name like '%Milk%' and name like '%Yogurt%'
order by cart_id