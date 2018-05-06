package edu.iot.butter.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import edu.iot.butter.model.Sensor;

@Service
public class SensorServiceImpl implements SensorService {
	static List<Sensor> testList = new ArrayList<>();
	static {
		testList.add(new Sensor(
					0, "온도", "temperature1", 0, 23.1, new Date()));
		testList.add(new Sensor(
					1, "온도", "temperature1", 0, 23.4, new Date()));
		testList.add(new Sensor(
					2, "온도", "temperature1", 0, 23.4, new Date()));
		testList.add(new Sensor(
					3, "온도", "temperature1", 0, 23.7, new Date()));
		testList.add(new Sensor(
					4, "온도", "temperature1", 0, 23.9, new Date()));
		testList.add(new Sensor(
					5, "온도", "temperature1", 0, 23.3, new Date()));
		testList.add(new Sensor(
					6, "온도", "temperature1", 0, 23.1, new Date()));
		testList.add(new Sensor(
					7, "온도", "temperature1", 0, 23.2, new Date()));
	}

	@Override
	public List<Sensor> getList() {
		return testList;
	}

	@Override
	public Sensor get(long id) {
		return testList.get((int)id);
	}

	@Override
	public boolean insert(Sensor sensor) {
		return testList.add(sensor);
	}

	@Override
	public boolean update(Sensor sensor) {
		long id = sensor.getId();
		return testList.set((int)id, sensor) != null;

	}

	@Override
	public boolean delete(long id) {
		
		return testList.remove((int)id) != null;
	}

}
