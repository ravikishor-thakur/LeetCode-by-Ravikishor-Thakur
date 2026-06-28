from functools import lru_cache

class Solution:
    def shoppingOffers(self, price, special, needs):
        n = len(price)

        @lru_cache(None)
        def dfs(state):
            needs = list(state)

            cost = sum(needs[i] * price[i] for i in range(n))

            for offer in special:
                new_needs = []
                valid = True

                for i in range(n):
                    if offer[i] > needs[i]:
                        valid = False
                        break
                    new_needs.append(needs[i] - offer[i])

                if valid:
                    cost = min(cost, offer[-1] + dfs(tuple(new_needs)))

            return cost

        return dfs(tuple(needs))