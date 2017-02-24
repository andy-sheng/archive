LIBRARY IEEE;
USE IEEE.STD_LOGIC_1164.ALL;
USE IEEE.STD_LOGIC_UNSIGNED.ALL;

ENTITY led IS
PORT(clk, choose,reset:IN STD_LOGIC;
	 cout:OUT STD_LOGIC_VECTOR(7 DOWNTO 0));
END led;

ARCHITECTURE a OF led IS
SIGNAL temp:STD_LOGIC_VECTOR(7 DOWNTO 0);
SIGNAL p:STD_LOGIC;
TYPE ALL_STATE IS(S0, S1, S2, S3, S4, S5, S6, S7, S8, S9, S10, S11);
SIGNAL STATE: ALL_STATE;
BEGIN
  PROCESS(clk, choose, reset)
  BEGIN
  if(reset='1')then state<=S0;temp<="00000000";p<='0';
  ELSIF(clk'event AND clk='1'and choose='1') THEN
	temp<="00000000";
    CASE state IS
	WHEN S0=>state<=S1;temp<="00000001";p<='0';
	WHEN S1=>
	  IF(p='0')THEN state<=S2;temp<="00000010";
	  ELSIF(p='1')THEN state<=S0;temp<="00000010";
	  END IF;
	WHEN S2=>
	  IF(p='0')THEN state<=S3;temp<="00000100";
	  ELSIF(p='1')THEN state<=S1;temp<="00000100";
	  END IF;
	WHEN S3=>
	  IF(p='0')THEN state<=S4;temp<="00001000";
	  ELSIF(p='1')THEN state<=S2;temp<="00001000";
	  end if;
	WHEN S4=>
	  IF(p='0')THEN state<=S5;temp<="00010000";
	  ELSIF(p='1')THEN state<=S3;temp<="00010000";
	  end if;
	when S5=>
	  if(p='0')then state<=S6;temp<="00100000";
	  elsif(p='1')then state<=S4;temp<="00100000";
	  end if;
	when S6=>
	  if(p='0')then state<=S7;temp<="01000000";
	  elsif(p='1')then state<=S5;temp<="01000000";
	  end if;
	when OTHERS=>state<=s6;temp<="10000000";p<='1';
	end case;
  ELSIF (clk'event AND clk='1'AND choose='0') THEN
	CASE state IS
	WHEN S0=>state<=S8;temp<="00011000";p<='0';
	WHEN S8=>
	  IF(p='0')THEN state<=S9;temp<="00111100";
	  ELSIF(p='1')THEN state<=S0;temp<="00000000";
	  END IF;
	WHEN S9=>
	  IF(p='0')THEN state<=S10;temp<="01111110";
	  ELSIF(p='1')THEN state<=S8;temp<="00011000";
	  END IF;
	WHEN S10=>
	  IF(p='0')THEN state<=S11;temp<="11111111";
	  ELSIF(p='1')THEN state<=S9;temp<="00111100";
	  END IF;
	WHEN OTHERS=>state<=S10;temp<="01111110";p<='1';
	END CASE;
  END IF;
  END PROCESS;
  cout<=temp;
END A;