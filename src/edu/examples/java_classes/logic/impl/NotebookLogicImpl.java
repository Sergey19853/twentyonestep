package edu.examples.java_classes.logic.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.examples.java_classes.dao.DaoProvider;
import edu.examples.java_classes.dao.NoteBookDao;
import edu.examples.java_classes.entity.Note;
import edu.examples.java_classes.logic.NotebookLogic;

public class NotebookLogicImpl implements NotebookLogic {
	private final DaoProvider provider = DaoProvider.getInstance();
	private final NoteBookDao dao = provider.getNoteBookDao();
	
	public void add(Note n) {		
		dao.save(n);
	}
	
	public void add(String title, String content) {
		Note n = new Note(title, content);
		dao.save(n);	
	}
	
	public void clear() {
		dao.allNotes().clear();
	}
	
	public void deleteEntry(int id) {
		
		List<Note> myNotes = dao.allNotes();
		int count = 0;
		
		for(Note note : myNotes) {
			if(note.getId() == id) {
				myNotes.remove(count);
				return;
			}
			count++;
		}
	
	}
	
	public List<Note> find(String text){
		
		List<Note> result = new ArrayList<Note>();
		
		List<Note> myNotes = dao.allNotes();
		
		for(Note n : myNotes) {
			if(isTextInNote(n, text)) {
				result.add(n);
			}
		}
		
		return result;

	}
	
	private boolean isTextInNote(Note n, String text) {
		return n.getTitle().contains(text) || n.getContent().contains(text);
	}
	
	
	public List<Note> find(Date date){
		
		List<Note> result = new ArrayList<Note>();
		
		List<Note> myNotes = dao.allNotes();
		
		// ...d1.equals(d2);
		for(Note n : myNotes) {
			if(n.getD().equals(date)) {
				result.add(n);
			}
		}
		
		return result;
		
	}
	

	public List<Note> allNotes(){
		return dao.allNotes();
	}

}
