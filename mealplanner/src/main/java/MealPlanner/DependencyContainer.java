package MealPlanner;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author micah
 */
public class DependencyContainer {
	private ComponentFactory componentFactory;
	private RepositoryFactory repositoryFactory;

	public ComponentFactory getComponentFactory(){
		if (componentFactory == null){
			componentFactory = new ComponentFactory(this);
		}
		return this.componentFactory;
	}

	public RepositoryFactory getRepositoryFactory(){
		if (repositoryFactory == null){
			repositoryFactory = new RepositoryFactory(this);
		}
		return repositoryFactory;
	}
}
