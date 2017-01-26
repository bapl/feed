# feed

数据库的数据，根据用户id分库，根据时间进行分表的机制

用户信息表（user_id,user_name.....）；
用户关注表(user_id,follow_user_id,follow_last_login_dt)；
用户发布信息统计表(维度天)（user_id,publish_id,publish_date,count....）；
用户具体发布的数据（user_id,publish_id,publish_date,publish_content,.....）
#
用户数据，分成3个
用戶发布数据，不直接存储到数据库，先放到redis缓存，并且更新相关的3个相关数据
用户ID为Ｋｅｙ　具体的数据（数据过期时间为一周）
用户feed 的索引的数据(redis 的sorted set，存储)（数据过期时间为一周）
用户最新的发布微博的时间
上面3个集合的key 都是用户ID

用户第一次刷新微博的逻辑
1> 根据用户关注的人最后发布的时间表排序，第一次获取前50名
2> 后台启动多线程根据过滤的人的id 查询redis 查询对应的自己发布索引数据，如果存在直接返回，不存在从数据库中获取10条数据，并把数据存放到redis缓存中；
3> 把查询的数据存在redis 的sortedSet里。根据timeline 作为sorce 来排序。
4> 然后返回第一页的数据。
5> 后台启动多线程把用户最新一个月的数据索引汇总排序，进行分页。
7> 用户点击下一页的时候如果后台的汇总一个月的数据还没有完成，那么根据第一次缓存的数据分页返回给用户。


