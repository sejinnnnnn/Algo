-- 코드를 작성해주세요
select count(*) as `FISH_COUNT`
from FISH_INFO f, FISH_NAME_INFO n
where f.FISH_TYPE = n.FISH_TYPE
    and n.FISH_NAME in ('BASS', 'SNAPPER');