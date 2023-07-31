select h.flavor
from first_half h inner join (select flavor, sum(total_order) total from july group by flavor) j
                             on j.flavor = h.flavor
order by h.total_order + j.total desc
    limit 3