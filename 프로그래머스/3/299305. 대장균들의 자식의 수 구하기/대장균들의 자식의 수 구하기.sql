select p.ID, 
    IFNULL((select count(*) from ECOLI_DATA e group by e.PARENT_ID having e.PARENT_ID = p.ID), 0) as `CHILD_COUNT`
from ECOLI_DATA p;