package com.darren.spring.dao.redis.impl;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import com.darren.spring.dao.redis.IRedisPersonDao;
import com.darren.spring.utils.redis.AbstractBaseRedisDao;
import com.darren.spring.vo.PersonVO;

import net.sf.json.JSONObject;

public class IRedisPersonDaoImpl extends AbstractBaseRedisDao<String, PersonVO> implements IRedisPersonDao {

	public boolean addPerson(final PersonVO personVo) {
		// TODO Auto-generated method stub
		boolean result=redisTemplate.execute(new RedisCallback<Boolean>() {
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				// TODO Auto-generated method stub
				boolean flag=true;
				try {
					RedisSerializer<String> serializer=getRedisSerializer();
					byte [] key =serializer.serialize(String.valueOf(personVo.getId()));
					byte [] val =serializer.serialize(JSONObject.fromObject(personVo).toString());
					connection.set(key, val);
				} catch (SerializationException e) {
					// TODO Auto-generated catch block
					flag=false;
					e.printStackTrace();
				}
				return flag;
			}
			
		});
		return result;
	}

	public void addPersonList(final List<PersonVO> personVoList) {
		// TODO Auto-generated method stub
		redisTemplate.execute(new RedisCallback<Boolean>() {

			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				// TODO Auto-generated method stub
				try {
					RedisSerializer<String> serializer=getRedisSerializer();
					for(PersonVO personVo:personVoList){
						byte [] key =serializer.serialize(String.valueOf(personVo.getId()));
						byte [] val =serializer.serialize(JSONObject.fromObject(personVo).toString());
						connection.set(key, val);
					}
				} catch (SerializationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return null;
			}
			
		});
	}

	public boolean removePersonVo(final String key) {
		// TODO Auto-generated method stub
		boolean result=redisTemplate.execute(new RedisCallback<Boolean>() {
				public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
					// TODO Auto-generated method stub
					boolean flag=true;
					try {
						RedisSerializer<String> redisSerializer=getRedisSerializer();
						connection.del(redisSerializer.serialize(key));
					} catch (SerializationException e) {
						flag=false;
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return flag;
				}
				
		});
		return result;
	}

	public boolean removePersonVoList(List<String> keyList) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean updatePersonVo(PersonVO personVo) {
		// TODO Auto-generated method stub
		return false;
	}

	public PersonVO getPersonVo(final String key) {
		// TODO Auto-generated method stub
		PersonVO result=redisTemplate.execute(new RedisCallback<PersonVO>() {

			public PersonVO doInRedis(RedisConnection connection) throws DataAccessException {
				// TODO Auto-generated method stub
				RedisSerializer<String> serializer=getRedisSerializer();
				byte [] keyId=serializer.serialize(key);
				if(keyId==null){
					return null;
				}
				String objectJson=serializer.deserialize(connection.get(keyId));
				return (PersonVO)JSONObject.toBean(JSONObject.fromObject(objectJson), PersonVO.class);
			}
			
		});
		
		return result;
	}

}
