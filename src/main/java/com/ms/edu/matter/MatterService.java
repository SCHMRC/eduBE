package com.ms.edu.matter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ms.edu.auth.AuthEntryPoint;
import com.ms.edu.model.Matter;
import com.ms.edu.model.MatterRepo;

import lombok.extern.java.Log;





@Service
@Log
public class MatterService {
	
	@Autowired
	private MatterRepo matterRepo;

	public MatterService() {}
	
	public boolean saveMatterTeacher(Matter matter) {
		long id = matter.getTeachers().get(0).getId();
		if(!this.matterRepo.existsById(matter.getMatter())) {
			this.matterRepo.saveMatter(matter.getMatter());
		}
		return this._responce(this.matterRepo.saveAddMatter(matter.getMatter(), id));	
	}
	
	public boolean removeMatterTeacher(String matter, Long teacherId) {
		return this._responce(this.matterRepo.removeMatterTech(matter, teacherId));	
	}
	
	private boolean _responce(Integer number) {
		boolean flag = false;
		if(number == 1) {
			flag = true;
		}
		return flag;
	}

}
