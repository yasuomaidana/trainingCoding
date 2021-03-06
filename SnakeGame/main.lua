function checkIfCollideWithItself(s)
    local head = getHead(s)
    for i = 1, #s-1 do
        local bodyPart = s[i]
        if head.x == bodyPart.x and head.y == bodyPart.y then
            return true
        end
    end
end
function checkIfOutOfBound(s)
    return(
        getHead(s).x > gridW or 
        getHead(s).x < 1 or 
        getHead(s).y > gridH or 
        getHead(s).y < 1 or
        checkIfCollideWithItself(s)
    )
end
function placeFood()
    food = {
        x = love.math.random(1,gridW),
        y = love.math.random(1,gridH),
    }
end
function checkIfNeedsToGrow(s,newHead)
    if food.x == newHead.x and food.y == newHead.y then
        placeFood()
        delayMovement = delayMovement * 0.9
    else
        table.remove(s,1)
    end
end
function getHead(s)
    return s[#s]
end
function moveSnake(s)
    --en lua los array inician en 0
    local newHead = {
        x=getHead(s).x,
        y=getHead(s).y
    }
    if direction == 'left' then newHead.x = newHead.x-1 end
    if direction == 'right' then newHead.x = newHead.x+1 end
    if direction == 'up' then newHead.y = newHead.y-1 end
    if direction == 'down' then newHead.y = newHead.y+1 end
    table.insert(s,newHead)
    if checkIfOutOfBound(s) then love.event.quit() end
    checkIfNeedsToGrow(s,newHead)
end
function love.load()
    gridW = 40
    gridH = 30
    snakePartSize = 20
    snake = {}
    direction = 'right'
    timerMovement = 0
    delayMovement = 0.2
    placeFood()
    for i=1,3 do
        table.insert(snake,{x=i,y=1})
    end
    local v = {1,2,3}
    love.graphics.setLineWidth(4)
    love.graphics.setLineStyle('smooth')
end

function love.update(dt)
    timerMovement = timerMovement + dt
    if timerMovement >= delayMovement then 
        moveSnake(snake)
        timerMovement = 0
    end
end

function love.draw()
    love.graphics.setColor(1,0,0)
    for i = 1, #snake do
        local part = snake[i]
        love.graphics.rectangle('line',
        (part.x-1)*snakePartSize,
        (part.y-1)*snakePartSize,
        snakePartSize,
        snakePartSize)
    end
    love.graphics.setColor(1,0.5,0)
    love.graphics.rectangle('fill',
    (food.x-1)*snakePartSize,
    (food.y-1)*snakePartSize,
    snakePartSize,
    snakePartSize)
end

function love.keypressed(k)
    if k == 'escape'then
        love.event.quit()
    elseif k == 'left' then
        if direction ~= 'right' then
            direction='left'
        end
    elseif k == 'right' then
        if direction ~= 'left' then
            direction='right'
        end
    elseif k == 'up' then
        if direction ~= 'down' then
            direction='up'
        end
    elseif k == 'down'then
        if direction ~= 'up' then
            direction='down'
        end
    end
end