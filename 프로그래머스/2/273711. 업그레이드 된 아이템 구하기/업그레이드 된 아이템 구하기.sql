-- 코드를 작성해주세요
select ci.ITEM_ID as `ITEM_ID`, ci.ITEM_NAME as `ITEM_NAME`, ci.RARITY
from ITEM_INFO ci, ITEM_INFO pi, ITEM_TREE t
where ci.ITEM_ID = t.ITEM_ID and pi.ITEM_ID = t.PARENT_ITEM_ID
    and t.PARENT_ITEM_ID is not null 
    and pi.RARITY = 'RARE'
order by ci.ITEM_ID desc;