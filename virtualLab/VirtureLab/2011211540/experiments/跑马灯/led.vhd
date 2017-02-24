library ieee;
use ieee.std_logic_1164.all;
use ieee.std_logic_unsigned.all;

entity led is
port(
	clk, choose, reset: in std_logic;
	cout: out std_logic_vector(7 downto 0));
end led;

architecture behav of led is
component sep is
port(
clk_in: in std_logic;
	clk_out: out std_logic
);
end component;
SIGNAL clkd: std_logic;
signal temp: std_logic_vector(7 downto 0);
signal p: std_logic;
type all_state is(S0, S1, S2, S3, S4, S5, S6, S7, S8, S9, S10, S11);
signal state: all_state;

begin
u1: sep port map(clk_in=>clk, clk_out=>clkd);

  process(clkd, choose, reset)
  begin
  if(reset='1')then state<=S0;temp<="00000000";p<='0';
  elsif(clkd'event and clkd='1'and choose='1') then
	temp<="00000000";
    case state is
	when S0=>state<=S1;temp<="00000001";p<='0';
	when S1=>
	  if(p='0')then state<=S2;temp<="00000010";
	  elsif(p='1')then state<=S0;temp<="00000010";
	  end if;
	when S2=>
	  if(p='0')then state<=S3;temp<="00000100";
	  elsif(p='1')then state<=S1;temp<="00000100";
	  end if;
	when S3=>
	  if(p='0')then state<=S4;temp<="00001000";
	  elsif(p='1')then state<=S2;temp<="00001000";
	  end if;
	when S4=>
	  if(p='0')then state<=S5;temp<="00010000";
	  elsif(p='1')then state<=S3;temp<="00010000";
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
  elsif (clkd'event and clkd='1'and choose='0') then
	case state is
	when S0=>state<=S8;temp<="00011000";p<='0';
	when S8=>
	  if(p='0')then state<=S9;temp<="00111100";
	  elsif(p='1')then state<=S0;temp<="00000000";
	  end if;
	when S9=>
	  if(p='0')then state<=S10;temp<="01111110";
	  elsif(p='1')then state<=S8;temp<="00011000";
	  end if;
	when S10=>
	  if(p='0')then state<=S11;temp<="11111111";
	  elsif(p='1')then state<=S9;temp<="00111100";
	  end if;
	when others=>state<=S10;temp<="01111110";p<='1';
	end case;
  end if;
  end process;
  cout<=temp;
end behav;