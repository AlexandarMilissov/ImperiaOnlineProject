package ajbdnlkjasg;

public abstract class WarfareUnit {
	
	public String name;
	WarfareTile tile;
	int hp;
	int attack;
	int defence;
	int range;
	int speed;
	WarfareSide side;
	int cost;
	int usesPerTern = 2;
	int timesLeft = usesPerTern;
	
	public WarfareUnit() {}
	
	/*
	public WarfareUnit(int X, int Y, int HP, int Attack, int Range, int Speed, WarefareSide Side)
	{
		x = X;
		y = Y;
		hp = HP;
		attack = Attack;
		range = Range;
		speed = Speed;
		side = Side;
	}
	*/
	public void Die()
	{
		side.units.remove(this);
		tile.RemoveUnit();
	}
	
	public void Attack(WarfareUnit Target)
	{
		if(Target.defence >= this.attack)
		{
			System.out.println("you are too weak");
			return;
		}
		else
		{
			Target.hp -= (this.attack - this.defence);
			if(Target.hp <= 0)
			{
				Target.Die();
			}
		}
	}
	public void DoAction()
	{
		timesLeft--; 
	}
	public void ResetActions()
	{
		timesLeft = usesPerTern;
	}
}
