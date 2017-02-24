library ieee;
use ieee.std_logic_1164.all;
use ieee.std_logic_arith.all;

entity counting is
port(
	clk, rs: in std_logic;
	countout: out std_logic_vector(3 downto 0));
end counting;

architecture behav of counting is
component sep is
port(
clk_in: in std_logic;
	clk_out: out std_logic
);
end component;
SIGNAL clkd: std_logic;
signal nextcount: integer range 0 to 11;

begin
u1: sep port map(clk_in=>clk, clk_out=>clkd);

process(rs, clkd)
begin
	if(clkd'event and clkd='1')then
		if rs='1' then
			nextcount <= 0;
		else nextcount <= nextcount + 1;
		end if;
	end if;
end process;
	countout <= conv_std_logic_vector(nextcount, 4);
end behav;