library ieee;
use ieee.std_logic_1164.all;
use ieee.std_logic_unsigned.all;

entity led is
port(
	clk: in std_logic;
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

  process(clkd)
  begin
  if(clkd'event and clkd='1') then
	temp<="11111111";
    case state is
	when S0=>state<=S1;temp<="11111110";p<='0';
	when S1=>
	  if(p='0')then state<=S2;temp<="11111101";
	  elsif(p='1')then state<=S0;temp<="11111101";
	  end if;
	when S2=>
	  if(p='0')then state<=S3;temp<="11111011";
	  elsif(p='1')then state<=S1;temp<="11111011";
	  end if;
	when S3=>
	  if(p='0')then state<=S4;temp<="11110111";
	  elsif(p='1')then state<=S2;temp<="11110111";
	  end if;
	when S4=>
	  if(p='0')then state<=S5;temp<="11101111";
	  elsif(p='1')then state<=S3;temp<="11101111";
	  end if;
	when S5=>
	  if(p='0')then state<=S6;temp<="11011111";
	  elsif(p='1')then state<=S4;temp<="11011111";
	  end if;
	when S6=>
	  if(p='0')then state<=S7;temp<="10111111";
	  elsif(p='1')then state<=S5;temp<="10111111";
	  end if;
	when OTHERS=>state<=s6;temp<="01111111";p<='1';
	end case;
  end if;
  end process;
  cout<=temp;
end behav;