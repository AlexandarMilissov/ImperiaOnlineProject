package ajbdnlkjasg;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class WarfareSide {
	List<WarfareUnit> units;
	Color colour;
	WarfareField field;
	int isEven;
	WarfareTools tools;
	String name;
	Warfare parent;
	WarfareUnit selectedNewUnit = null;
	int maxActions = 5;
	int actionsRemaining = maxActions;
	int money = 200;
	
	public WarfareSide(List<WarfareUnit> Units, Color Colour,WarfareField Field, int IsEven, String Name,Warfare Parent)
	{

		name = Name;
		tools = new WarfareTools();
		tools.thisSide = this;
		tools.Name.setText(Name);
		units = Units;
		colour = Colour;
		field= Field;
		isEven = IsEven;
		parent = Parent;		
		tools.UpdateActionsCounter(actionsRemaining);
		tools.UpdateMoneyCounter(money);
	}
	
	public void SelectNewUnit(WarfareUnit Unit)
	{
		selectedNewUnit = Unit;
		if(Unit == null)
		{
			return;
		}
		selectedNewUnit.side = this;
	}
	
	public void AddtheNewUnit(WarfareTile Tile)
	{
		units.add(selectedNewUnit);
		selectedNewUnit.tile = Tile;
		selectedNewUnit.tile.unit = selectedNewUnit;
		selectedNewUnit = null;
		tools.unit = null;
		tools.UpdateMoneyCounter(money);
	}
	public void EndTurn()
	{
		if(!(parent.turnCounter%2 == isEven))
		{
			this.parent.UpdateTurnCounters();
			tools.UpdateActionsCounter(actionsRemaining);
			actionsRemaining = maxActions;
			for(var u : units)
			{
				u.ResetActions();
			}
		}
	}
	public void DoAction()
	{
		actionsRemaining--;
		tools.UpdateActionsCounter(actionsRemaining);
		System.out.println(actionsRemaining);
	}
}
