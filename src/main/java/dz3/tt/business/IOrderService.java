package dz3.tt.business;

import dz3.tt.dto.OrderDto;
import dz3.tt.exceptions.UserNotFoundException;

public interface IOrderService {
    OrderDto createOrder(Long userId) throws UserNotFoundException;
}
