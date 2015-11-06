1. create
- update GS state

1.1. join
1.2. leave

2. start
- update GS state
- create Match
- create Sets (based on number of users)

2.1. thread
- iterate Sets
- create Game
- listen users
- close Game:
  -- if create UserProduct
  -- next Game
- next Set

3. pause

4. stop
- update GS state